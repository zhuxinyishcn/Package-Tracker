package edu.unl.cse.csce361.package_tracker.logic;

public class DroneCall implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private Thread t;
	private String threadName;
	public static int destination = 0;

	public static void setCallDroneDestination(int destination) {
		DroneCall.destination = destination;
	}

	public DroneCall(String name) {
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
				System.out.println("Drone is taking off.");
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
				Thread.sleep(1000);
			}
			System.out.println("Drone " + droneID + "Arrived warehouse " + destination);
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
	}

}
