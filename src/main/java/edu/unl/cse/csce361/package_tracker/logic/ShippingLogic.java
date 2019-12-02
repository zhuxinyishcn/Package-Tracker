package edu.unl.cse.csce361.package_tracker.logic;

public class ShippingLogic {

	public static void hasNextWareHouse(String trackingNumber) {
		int current = 0;
		int destination = -1;
		// TODO: using @trackingNumber to get orig and dest.
		if (current == destination) {
			System.out.println("Your package has arrived. Please confirm receive.");
			PackageLogic.confirmPackage(trackingNumber);
		} else {
			nextWarehouse(trackingNumber, current, destination);
		}

	}

	public static void nextWarehouse(String trackingNumbner, int current, int destination) {
		if (current < destination) {
			deliverToNext(trackingNumbner, current++); 
		}else if (destination>current) {
			deliverToNext(trackingNumbner,current--);
		}else {
			System.out.println("System Error! Contact customer support. Tracking Number: "+trackingNumbner);
		}
	}

	public static void deliverToNext(String trackingNumbner, int nextWarehouse) {
		//TODO: //Count time.
		//TODO: using @trackingNumber @nextWarehouse set status
	}
}
