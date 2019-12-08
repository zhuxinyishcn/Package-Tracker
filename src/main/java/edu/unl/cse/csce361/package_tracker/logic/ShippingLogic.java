package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;

import edu.unl.cse.csce361.package_tracker.backend.Warehouse;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class ShippingLogic {
	public static ArrayList<Warehouse> warehouse = new ArrayList<>();

	public static void hasNextWareHouse(String trackingNumber) {
		int current = 0;
		int destination = -1;
		// TODO: using @trackingNumber to get current and destination.
		if (current == destination) {
			Printer.printLogicArrivePackage(trackingNumber);
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
		}
	}

//	public static void addWarehouse() {
//		warehouse.add(new WarehouseConstructor(1,
//				String.format("%-5s %-30s %-50s", "1", "Seward Center", "800 N Columbia Ave, Seward, NE 68434"),
//				40.914026, -97.087167));
//		warehouse.add(new WarehouseConstructor(2,
//				String.format("%-5s %-30s %-50s", "2", "Milford Center", "923 238th Rd, Milford, NE 68405"), 40.817575,
//				-97.045202));
//		warehouse.add(new WarehouseConstructor(3,
//				String.format("%-5s %-30s %-50s", "3", "Lincoln 112st", "790-926 NW 112th St, Lincoln, NE 68528"),
//				40.819617, -96.872919));
//		warehouse.add(new WarehouseConstructor(4,
//				String.format("%-5s %-30s %-50s", "4", "Lincoln Hub", "2701 O St, Lincoln, NE 68510"), 40.813314,
//				-96.682142));
//		warehouse.add(new WarehouseConstructor(5,
//				String.format("%-5s %-30s %-50s", "5", "O and 84", "100 N 84th St , Lincoln, NE 68505"), 40.81386,
//				-96.605443));
//		warehouse.add(new WarehouseConstructor(6,
//				String.format("%-5s %-30s %-50s", "6", "O and Nebraska", "8525 Andermatt Drive, Lincoln, NE 68526"),
//				40.735401, -96.603936));
//		warehouse.add(new WarehouseConstructor(7,
//				String.format("%-5s %-30s %-50s", "7", "I80 and 154 st", "14541 Castlewood St, Waverly, NE 68462"),
//				40.907863, -96.523535));
//		warehouse.add(new WarehouseConstructor(8,
//				String.format("%-5s %-30s %-50s", "8", "I80 and 250 st", "14599 250th St, Greenwood, NE 68366"),
//				40.988276, -96.369594));
//		warehouse.add(new WarehouseConstructor(9, String.format("%-5s %-30s %-50s", "9", "I80 and Nebraska Crossing",
//				"21209 Nebraska Crossing Dr, Gretna, NE 68028"), 41.098705, -96.250167));
//		warehouse.add(new WarehouseConstructor(10,
//				String.format("%-5s %-30s %-50s", "10", "I80 and 118th St", "6271 S 118th St, Omaha, NE 68137"),
//				41.196003, -96.093617));
//		warehouse.add(new WarehouseConstructor(11,
//				String.format("%-5s %-30s %-50s", "11", "Omaha Hub", "3110 Farnam St, Omaha, NE 68131"), 41.258746,
//				-95.958241));
//		warehouse
//				.add(new WarehouseConstructor(12, String.format("%-5s %-30s %-50s", "12", "North Omaha(Missouri River)",
//						"9100 John J Pershing Dr, Omaha, NE 68112, USA"), 41.343043, -95.958519));
//	}

}
