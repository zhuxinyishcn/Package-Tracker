package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class ShippingLogic {
	public static ArrayList<Drone> drone = new ArrayList<Drone>();
	private static final logicFacade logic = logicFacade.getInstance();

	public static void addDrone() {
		Drone a = new Drone("Idle", 1);
		drone.add(a);
		int f = 0;
		for (int i = 0; i < 1000; i++) { //Java somethine skip this method.
			f++;
		} 
		Drone b = new Drone("Idle", 12);
		drone.add(b);
	}

	public static boolean checkAvilability() {
		int check = 0;

		for (int i = 0; i < 2; i++) {
			if (drone.get(i).getStatus().equals("Idle")) {
				check = i;
			}
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}// TODO : REMOVE RYS

	public static int findAvilableDrone() {
		int check = 0;
		for (int i = 0; i < 2; i++) {
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

	public static void hasNextWareHouse(String trackingNumber) {
		int current = 0;
		int destination = -1;
		// TODO: using @trackingNumber to get current and destination.
		if (current == destination) {
			System.out.println("Your package has arrived. Please confirm receive.");
			AdminLogic.confirmPackage(trackingNumber);
		} else {
//			nextWarehouse(trackingNumber, current, destination);
		}
	}

	public static void callDrone(String warehouseID) {
		if (logic.isNumber(warehouseID)) {
			logic.setCallDroneDestination(Integer.parseInt(warehouseID));
			CallDrone R1 = new CallDrone("Drone-1");
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

	public static void main(String args[]) throws InterruptedException {
		logic.addDrone();
		ShippingLogic.callDrone("10");
	}
}
