package edu.unl.cse.csce361.package_tracker.logic;

public class DronePickUp implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private Thread t;
	private String threadName;
	public static int destination = -1;
	public static double distanceToWarehouse = -1;
	public static String trackingNumber = "";

	public static void setPickUpDestination(int destination) {
		DronePickUp.destination = destination;
	}

	public static void setPickUprackingNumber(String trackingNumber) {
		DronePickUp.trackingNumber = trackingNumber;
	}

	public static void setPickUpDistanceToWarehouse(double distance) {
		DronePickUp.distanceToWarehouse = distance;
	}

	public DronePickUp(String name) {
		threadName = name;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void run() {
		try {
			int droneID = logic.findAvilableDrone();
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) != 0) {
				System.out.println("Drone" + droneID + " is taking off to warehouse " + destination
						+ " to preprare to pickup your package.");
				int nextLocation = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination);
				logic.getDrone().get(droneID).setStatus("Calling");
				int time = logic.findTimeNeededForWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						nextLocation);
				int nextWarehouse = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination);
				logic.getDrone().get(droneID).setCurrentLocation(nextWarehouse);

				// 让线程睡眠一会
				Thread.sleep(time);
				System.out.println("Drone " + droneID + " Arrive warehosue: "
						+ logic.getDrone().get(droneID).getCurrentLocation());
			}
			Thread.sleep((int) distanceToWarehouse * 1000);
			System.out.println("Drone " + droneID + " come to your address and picked up the package.");
			Thread.sleep((int) distanceToWarehouse * 1000);
			logic.editStatus(trackingNumber, "Dispatching");
			System.out.println(
					"Drone " + droneID + " Arrived warehouse " + destination + " with your package. Dispatching.");

		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted. Please contact support for help.");
		}
	}

}
