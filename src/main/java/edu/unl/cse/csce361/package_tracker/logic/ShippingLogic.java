package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;

public class ShippingLogic {

    public static ArrayList<String> warehouse = new ArrayList<String>();

    public static void hasNextWareHouse (String trackingNumber) {
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

    public static void nextWarehouse (String trackingNumbner, int current, int destination) {
        if (current < destination) {
            deliverToNext(trackingNumbner, current++);
        } else if (destination > current) {
            deliverToNext(trackingNumbner, current--);
        } else {
            System.out.println("System Error! Contact customer support. Tracking Number: " + trackingNumbner);
        }
    }

	public static void addWarehouse() {
		warehouse.add(
				String.format("%-5s %-30s %-50s", "1", "Seward", "Weller Hall, 800 N Columbia Ave, Seward, NE 68434"));
		warehouse.add(String.format("%-5s %-30s %-50s", "2", "Milford", "923 238th Rd, Milford, NE 68405"));
		warehouse.add(String.format("%-5s %-30s %-50s", "3", "Lincoln 112st", "790-926 NW 112th St, Lincoln, NE 68528"));
		warehouse.add(String.format("%-5s %-30s %-50s", "4", "Lincoln Hub", "2701 O St, Lincoln, NE 68510"));
		warehouse.add(String.format("%-5s %-30s %-50s", "5", "O and 84",
				"Weller Hall, 800 N Columbia Ave, Seward, NE 68434"));
		warehouse.add(
				String.format("%-5s %-30s %-50s", "6", "O and Nebraska", "8525 Andermatt Drive, Lincoln, NE 68526"));
		warehouse.add(
				String.format("%-5s %-30s %-50s", "7", "I80 and 154 st", "14541 Castlewood St, Waverly, NE 68462"));
		warehouse.add(String.format("%-5s %-30s %-50s", "8", "I80 and 250 st", "14599 250th St, Greenwood, NE 68366"));
		warehouse.add(String.format("%-5s %-30s %-50s", "9", "I80 and Nebraska Crossing",
				"21209 Nebraska Crossing Dr, Gretna, NE 68028"));
		warehouse.add(String.format("%-5s %-30s %-50s", "10", "I80 and 118th St", "6271 S 118th St, Omaha, NE 68137"));
		warehouse.add(String.format("%-5s %-30s %-50s", "11", "Omaha Hub", "3110 Farnam St, Omaha, NE 68131"));
		warehouse.add(String.format("%-5s %-30s %-50s", "12", "Missouri River", "4501 Abbott Dr, Omaha, NE 68110"));
	}
}
