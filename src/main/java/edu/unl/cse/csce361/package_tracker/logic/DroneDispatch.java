package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
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
		int destination = logic.getDispatchingPackage().get(packageID).getReceiver().getDestination();
		try {
			int droneID = logic.findAvilableDrone();
			logic.getDrone().get(droneID).setTrackingNumber(trackingNumber);
			while (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) != 0) {
				System.out.println("Drone " + droneID + " is taking off with your package.");
				int nextLocation = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination);
				int time = logic.findTimeNeededForWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						nextLocation);
				int nextWarehouse = logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(),
						destination);
				logic.getDrone().get(droneID).setCurrentLocation(nextWarehouse);
				BACKEND_FACADE.editCurrentlocation(trackingNumber, nextWarehouse);
				Thread.sleep(time);
				System.out.println("Drone " + droneID + " Arrive warehosue: "
						+ logic.getDrone().get(droneID).getCurrentLocation() + " with your package");

				Thread.sleep(1000);
			}

			if (logic.findNextWarehouse(logic.getDrone().get(droneID).getCurrentLocation(), destination) == 0) {
				System.out.println("Drone " + droneID + " is out of delivery for your package");
				Thread.sleep((int) logic
						.findClosestWarehouse(
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLatitude(),
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLongitude())
						.getDistance() * 1000);
				System.out.println(
						"Drone " + droneID + " has deliver your package, Please confirm your package arrived.");
				BACKEND_FACADE.editPackageStatus(trackingNumber, "Arrived");
				Thread.sleep((int) logic
						.findClosestWarehouse(
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLatitude(),
								logic.getDispatchingPackage().get(packageID).getReceiver().getAddress().getLongitude())
						.getDistance() * 1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted. Please contact support for help.");
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
