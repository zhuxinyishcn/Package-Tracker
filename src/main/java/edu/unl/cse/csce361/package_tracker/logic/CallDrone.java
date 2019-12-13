package edu.unl.cse.csce361.package_tracker.logic;

public class CallDrone implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private Thread t;
	private String threadName;
	public static boolean running = true;
	public static int destination = 0;

	public static void setDestination(int destination) {
		CallDrone.destination = destination;
	}

	public static void setRunning(boolean running) {
		CallDrone.running = running;
	}

	public CallDrone(String name) {
		threadName = name;

		// TODO: Move printer
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void run() {
		try {
			System.out.println("Drone is Comming");
			int droneID = logic.findAvilableDrone();
			int next = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination);
			if (next == 0) {
				System.out.println("Drone already here!");
			}
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) != 0) {
				int nextLocation = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination);
				logic.getDrone().get(droneID).setStatus("Calling");
				int time = logic.findTimeNeededForWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						nextLocation);
				int nextWarehouse = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination);
				logic.getDrone().get(droneID).setCurrentLocation(nextWarehouse);

				// 让线程睡眠一会
				Thread.sleep(time);
				System.out.println(
						"Drone " + droneID + "Arrive warehosue: " + logic.getDrone().get(droneID).getCurrentLocation());
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
	}

}
