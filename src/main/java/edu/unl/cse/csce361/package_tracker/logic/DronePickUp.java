package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class DronePickUp implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
	private Thread t;
	private String threadName;
	private int destination;
	private double distanceToWarehouse;
	private String trackingNumber;

	public DronePickUp(String name, String trackingNumber, double distanceToWarehouse, int destination) {
		threadName = name;
		this.trackingNumber = trackingNumber;
		this.destination = destination;
		this.distanceToWarehouse = distanceToWarehouse;
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
			logic.getDrone().get(droneID).setStatus("Calling");
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) != 0) {
				System.out.println("Drone " + droneID + " is taking off to warehouse " + destination
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
				Printer.PrintDroneArrive(droneID, logic.getDrone().get(droneID).getCurrentLocation(),
						"to pickup your package");
			}
			Thread.sleep((int) distanceToWarehouse * 1000);
			System.out.println("Drone " + droneID + " come to your address and picked up the package.");
			Thread.sleep((int) distanceToWarehouse * 1000);
			BACKEND_FACADE.editPackageStatus(trackingNumber, "Dispatching");
			logic.getDrone().get(droneID).setStatus("Idle");
			Printer.printDispatching(droneID, destination);

		} catch (InterruptedException e) {
			Printer.printThreadException(threadName);
		}
	}

}
