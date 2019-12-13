package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;
import java.util.List;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Drone;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class ShippingLogic {
	public static ArrayList<Drone> drone = new ArrayList<Drone>();
	public static List<Package> dispatchingPackage;
	private static final logicFacade logic = logicFacade.getInstance();
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();

	public static List<Package> getDispatchingPackage() {
		return dispatchingPackage;
	}

	public static void setDispatchingPackage(List<Package> dispatchingPackage) {
		ShippingLogic.dispatchingPackage = dispatchingPackage;
	}

	public static void addDrone() {
		Drone a = new Drone(1, "Idle", 1, null);
		drone.add(a);
		int f = 0;
		for (int i = 0; i < 1000; i++) { // Java somethine skip this method.
			f++;
		}
		Drone b = new Drone(1, "Idle", 12, null);
		drone.add(b);
	}

	public static boolean checkAvilability() {
		int check = 0;

		for (int i = 0; i < drone.size(); i++) {
			if (drone.get(i).getStatus().equals("Idle")) {
				check = check + 1;
			}
		}
		if (check != 0) {
			return true;
		} else {
			return false;
		}
	}// TODO : REMOVE RYS

	public static int findAvilableDrone() {
		int check = 0;
		for (int i = 0; i < drone.size(); i++) {
			if (drone.get(i).getStatus().equals("Idle")) {
				check = i;
			}
		}
		return check;
	}

	public static int findNextWarehouse(int current, int destination) {
		if (current < destination) {
			return current + 1;
		}
		if (current > destination) {
			return current - 1;
		} else {
			return 0;
		}
	}

	public static void callDrone(String warehouseID) {
		if (logic.isNumber(warehouseID)) {
			DroneCall R1 = new DroneCall("CallDrone_Request_" + warehouseID, Integer.parseInt(warehouseID));
			R1.start();

		} else {
			Printer.printInvalid();
		}
	}

	public static int findTimeNeededForWarehouse(int current, int destination) {
		double distance = logic.CalculateDistance(logic.getWarehouse().get(current - 1).getAddress().getLatitude(),
				logic.getWarehouse().get(current - 1).getAddress().getLongitude(),
				logic.getWarehouse().get(destination - 1).getAddress().getLatitude(),
				logic.getWarehouse().get(destination - 1).getAddress().getLongitude());
		return (int) (distance * 1000);
	}

	public static void droneUpdate() {
		DroneUpdate R1 = new DroneUpdate("CallDrone_Request");
		R1.start();
	}

	public static void dispatchingPackages(String name, String trackingNumber) {
		DroneDispatch d1 = new DroneDispatch(name, trackingNumber);
		d1.run();
	}

}
