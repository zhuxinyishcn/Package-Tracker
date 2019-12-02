package edu.unl.cse.csce361.package_tracker.logic;

public class ShippingLogic {
	PackageLogic pl = new PackageLogic();
	ShippingLogic sl = new ShippingLogic();

	public void hasNextWareHouse(String trackingNumber) {
		int current = 0;
		int destination = -1;
		// TODO: using @trackingNumber to get orig and dest.
		if (current == destination) {
			System.out.println("Your package has arrived. Please confirm receive.");
			pl.confirmPackage(trackingNumber);
		} else {
			sl.nextWarehouse(trackingNumber, current, destination);
		}

	}

	public void nextWarehouse(String trackingNumbner, int current, int destination) {
		if (current < destination) {
			sl.deliverToNext(trackingNumbner, current++); 
		}else if (destination>current) {
			sl.deliverToNext(trackingNumbner,current--);
		}else {
			System.out.println("System Error! Contact customer support. Tracking Number: "+trackingNumbner);
		}
	}

	public void deliverToNext(String trackingNumbner, int nextWarehouse) {
		//TODO: //Count time.
		//TODO: using @trackingNumber @nextWarehouse set status
	}
}
