package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;

import edu.unl.cse.csce361.package_tracker.backend.WarehouseConstructor;

public class ShippingLogic {
	private static final logicFacade logic = logicFacade.getInstance();
	public static ArrayList<WarehouseConstructor> warehouse = new ArrayList<WarehouseConstructor>();

	public static void hasNextWareHouse(String trackingNumber) {
		int current = 0;
		int destination = -1;
		// TODO: using @trackingNumber to get current and destination.
		if (current == destination) {
			System.out.println("Your package has arrived. Please confirm receive.");
			AdminLogic.confirmPackage(trackingNumber);
		} else {
			nextWarehouse(trackingNumber, current, destination);
		}
	}

	public static void deliverToNext(String trackingNumbner, int nextWarehouse) {
		// TODO: //Count time.
		// TODO: using @trackingNumber @nextWarehouse set status
	}

	public static void nextWarehouse(String trackingNumbner, int current, int destination) {
		if (current < destination) {
			deliverToNext(trackingNumbner, current++);
		} else if (destination > current) {
			deliverToNext(trackingNumbner, current--);
		} else {
			System.out.println("System Error! Contact customer support. Tracking Number: " + trackingNumbner);
		}
	}

	public static int findClosestWarehouse(double lat, double lon) {
		double finaldistance = 1000000;
		int warehouseID = -1;
		double distance = 0;
		for (int i = 0; i <= warehouse.size() - 1; i++) {
			distance = logic.distance(lat, lon, warehouse.get(i).getLatitude(), warehouse.get(i).getLongitude());
			if (finaldistance > distance) {
				finaldistance = distance;
				warehouseID = warehouse.get(i).getId();
			}
		}
		if (finaldistance > 10.01) {
			System.err.println("You are not in services range. You are " + distance
					+ " miles from the closest warehouse. The maximum distance for dilvery is 10 miles from warehouse.");
			return -1;
		}
		return warehouseID;
	}

	public static void addWarehouse() {
		warehouse.add(new WarehouseConstructor(1,
				String.format("%-5s %-30s %-50s", "1", "Seward", "Weller Hall, 800 N Columbia Ave, Seward, NE 68434"),
				40.915375, -97.085818));
		warehouse.add(new WarehouseConstructor(2,
				String.format("%-5s %-30s %-50s", "2", "Milford", "923 238th Rd, Milford, NE 68405"), 40.818924,
				-97.043853));
		warehouse.add(new WarehouseConstructor(3,
				String.format("%-5s %-30s %-50s", "3", "Lincoln 112st", "790-926 NW 112th St, Lincoln, NE 68528"),
				40.820966, -96.87157));
		warehouse.add(new WarehouseConstructor(4,
				String.format("%-5s %-30s %-50s", "4", "Lincoln Hub", "2701 O St, Lincoln, NE 68510"), 40.813335,
				-96.682003));
		warehouse.add(new WarehouseConstructor(5,
				String.format("%-5s %-30s %-50s", "5", "O and 84", "Weller Hall, 800 N Columbia Ave, Seward, NE 68434"),
				40.814094, -96.60496));
		warehouse.add(new WarehouseConstructor(6,
				String.format("%-5s %-30s %-50s", "6", "O and Nebraska", "8525 Andermatt Drive, Lincoln, NE 68526"),
				40.735531, -96.603739));
		warehouse.add(new WarehouseConstructor(7,
				String.format("%-5s %-30s %-50s", "7", "I80 and 154 st", "14541 Castlewood St, Waverly, NE 68462"),
				40.909212, -96.522186));
		warehouse.add(new WarehouseConstructor(8,
				String.format("%-5s %-30s %-50s", "8", "I80 and 250 st", "14599 250th St, Greenwood, NE 68366"),
				40.989625, -96.368245));
		warehouse.add(new WarehouseConstructor(9, String.format("%-5s %-30s %-50s", "9", "I80 and Nebraska Crossing",
				"21209 Nebraska Crossing Dr, Gretna, NE 68028"), 41.100054, -96.248819));
		warehouse.add(new WarehouseConstructor(10,
				String.format("%-5s %-30s %-50s", "10", "I80 and 118th St", "6271 S 118th St, Omaha, NE 68137"),
				41.197352, -96.092268));
		warehouse.add(new WarehouseConstructor(11,
				String.format("%-5s %-30s %-50s", "11", "Omaha Hub", "3110 Farnam St, Omaha, NE 68131"), 41.260095,
				-95.956892));
		warehouse.add(new WarehouseConstructor(12,
				String.format("%-5s %-30s %-50s", "12", "Missouri River", "4501 Abbott Dr, Omaha, NE 68110"), 41.300912,
				-95.898407));
	}

	public static void main(String[] args) {
		addWarehouse();
//		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
//		for (int i = 0; i <= warehouse.size() - 1; i++) {
//			System.out.println(warehouse.get(i).toString());
//		}
		System.out.println(findClosestWarehouse(40.818544,-96.699132));
	}
}
