package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class DroneDispatch implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
	private Thread t;
	private String threadName;
	private String trackingNumber;

	public DroneDispatch(String name, String trackingNumber) {
		threadName = name;
		this.trackingNumber = trackingNumber;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void run() {
		int packageID = findPackageID(trackingNumber);
		int droneID = logic.findAvilableDrone();
		int destination = logic.getDispatchingPackage().get(packageID).getReceiver().getDestination();
		int origian = logic.getDispatchingPackage().get(packageID).getCurrentLocation();
		try {
			logic.getDrone().get(droneID).setStatus("Calling");
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), origian) != 0) {
				Printer.PrintDroneTakeOff(droneID, logic.getDrone().get(droneID).getCurrentLocation(),
						"to pickup your package");
				int nextLocation = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), origian);
				logic.getDrone().get(droneID).setStatus("Calling");
				int time = logic.findTimeNeededForWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						nextLocation);
				int nextWarehouse = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						origian);
				logic.getDrone().get(droneID).setCurrentLocation(nextWarehouse);

				Thread.sleep(time);
				Printer.PrintDroneArrive(droneID, logic.getDrone().get(droneID).getCurrentLocation(), "");
				Thread.sleep(1000);
			}

			int destination1 = logic.getDispatchingPackage().get(packageID).getReceiver().getDestination();
			logic.getDrone().get(droneID).setTrackingNumber(trackingNumber);
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination1) != 0) {
				logic.getDrone().get(droneID).setStatus("Dispatching");
				destination1 = logic.getDispatchingPackage().get(packageID).getReceiver().getDestination();
				Printer.PrintDroneTakeOff(droneID, logic.getDrone().get(droneID).getCurrentLocation(),
						"with your package");
				int nextLocation = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination1);
				int time = logic.findTimeNeededForWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						nextLocation);
				int nextWarehouse = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination1);
				logic.getDrone().get(droneID).setCurrentLocation(nextWarehouse);
				logic.getDispatchingPackage().get(packageID).setCurrentLocation(nextWarehouse);
				BACKEND_FACADE.editCurrentlocation(trackingNumber, nextWarehouse);
				Thread.sleep(time);
				Printer.PrintDroneArrive(droneID, logic.getDrone().get(droneID).getCurrentLocation(),
						"with your package");
				Thread.sleep(1000);
			}

			if (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) == 0) {
				Printer.printOurForDelivery(droneID, logic.getDrone().get(droneID).getCurrentLocation());
				Thread.sleep((int) logic
						.findClosestWarehouse(
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLatitude(),
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLongitude())
						.getDistance() * 1000);
				Printer.printDevlivered(droneID);

				logic.getDrone().get(droneID).setStatus("Idle");
				BACKEND_FACADE.editPackageStatus(trackingNumber, "Arrived");
				logic.getDrone().get(droneID).setTrackingNumber(null);
				Thread.sleep((int) logic
						.findClosestWarehouse(
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLatitude(),
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLongitude())
						.getDistance() * 1000);
				logic.getDispatchingPackage().remove(packageID);
			}
		} catch (InterruptedException e) {
			Printer.printThreadException(threadName);
		}
	}

	public int findPackageID(String trackingNumber) {
		for (int i = 0; i < logic.getDispatchingPackage().size(); i++) {
			if (trackingNumber == logic.getDispatchingPackage().get(i).getTrackingNumber()) {
				return i;
			}
		}
		return -1;
	}
}
