package edu.unl.cse.csce361.package_tracker.logic;

public class DroneDispatch implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private Thread t;
	private String threadName;
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

	public DroneDispatch(String name) {
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

			// 让线程睡眠一会
			Thread.sleep(time);

		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted. Please contact support for help.");
		}
	}
}
