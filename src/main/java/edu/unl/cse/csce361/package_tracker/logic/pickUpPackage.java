package edu.unl.cse.csce361.package_tracker.logic;

public class pickUpPackage implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private Thread t;
	private String threadName;
	public static int destination = 0;
	public static double distanceToWarehouse = 0;

	public static void setDestination(int destination) {
		pickUpPackage.destination = destination;
	}

	public static void setDistanceToWarehouse(double distance) {
		pickUpPackage.distanceToWarehouse = distance;
	}

	public pickUpPackage(String name) {
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
				System.out.println("Drone is taking off to pickup your package.");
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
			System.out.println("Drone " + droneID + " Arrive your house and pickup the package.");
			Thread.sleep((int) distanceToWarehouse * 1000);
			System.out.println("Drone " + droneID + "Arrived warehouse " + destination
					+ " and it is comming to your address to pick up.");

		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted. Please contact support for help.");
		}
	}

}
