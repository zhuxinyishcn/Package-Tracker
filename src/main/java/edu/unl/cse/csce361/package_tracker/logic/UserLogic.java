package edu.unl.cse.csce361.package_tracker.logic;

public class UserLogic {

	private static logicFacade logic = logicFacade.getInstance();

	public static void becomeVIP(String userLogin) {
		// TODO: using @login to set user as vip.
		System.out.println("You have successfully become a VIP");
	}

	public static void returnPackage(String trackingNumber) {
		System.out.println("Your package had been successfully returned tracking number: " + trackingNumber);
	}

	public static void checkPackage(String trackingNumber, String login, boolean isSender, boolean onGoing) {
		if (trackingNumber != null) {
			// TODO: Using @trackingNumber find package info
			System.out.println(String.format("%-20s %-10s %-10s %-10s %-10s", "Tracking Number", "Sender", "Receiver",
					"Current Location", "Status"));
		}
		if (login != null) {
			if (isSender) {
				if (!onGoing) {
					// TODO: using @login who is a sender to find package info
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
				if (onGoing) {
					// TODO: using @login who is a sender to find package info which is in transit
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
			}
			if (!isSender) {
				if (!onGoing) {
					// TODO: using @login who is a receiver to find package info
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
				if (onGoing) {
					// TODO: using @login who is a receiver to find package info which is in
					// transit
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
			}
		}
	}

	public static void newPackage(String login, String destinationLogin) {
		// TODO: @login and @desinationLogin to create new package.
		String trackingNumber = null;
		System.out.println("You have successfully create a new package, your tacking number is: " + trackingNumber);
	}

	public static void cancelPackage(String trackingNumber) { // Without return services
		System.out.println("Your package: " + trackingNumber + " had been canceled.");
	}

	public static void holdAtWarehouse(String trackingNumber) {
		// TODO: Set @trackingNumber to hold.
		// TODO: Get current location to @warehouseID.
		int warehouseID = 1;
		System.out.println("Your package will be hold at: ");
		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
		System.out.println(logic.getWarehouse().get(warehouseID).toString());
	}

	public static void estimatePackage(String trackingNumber) {
		int eachLocationTime = 30; // Each stop 30 second
		int current = 0;
		int destination = 0;
		// TODO: Using @trackingNumber to get @current @destination
		int estimate = Math.abs(current - destination) * eachLocationTime + 30;// From last warehouse to final location.
		System.out.println("Your estimate deliver time is in " + estimate + " second.");
	}

	public static void arriveNotify(String trackingNumber) {
		// TODO: How to know package arrived.
		System.out.println("Your package: " + trackingNumber + " has arrived.");
	}

	public static void confirmReceive(String trackingNumber) {
		logic.returnPackage(trackingNumber);
		System.out.println("You have confirm receive of " + trackingNumber);
	}
}
