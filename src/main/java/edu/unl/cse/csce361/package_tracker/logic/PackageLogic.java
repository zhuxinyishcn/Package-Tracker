package edu.unl.cse.csce361.package_tracker.logic;

public class PackageLogic {

	public static void newPackage(String login, String destinationLogin) {
//		String login = null;
//		System.out.println("Please input your login:");
//		scan.userIn();
		// TODO: @login and @desinationLogin to create new package.
		System.out.println();
	}

	public static void cancelOrder(String trackingNumber) { // Without return services
//		String trackingNumber = null;
//		System.out.println("Please input your trackingNumber:");
//		scan.userIn();
		// TODO: Set @trackingNumber to cancelled.
	}

	public static void cancelPackage(String trackingNumber) { // With return srevices
		int current = 0;
		int sender = 0;
		// TODO: Using @trackingNumber to get @current @sender
		// TODO: Using @trackingNumber set @sender as receiver
	}

	public static void changeDestitation(String trackingNumber, String destitationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destitation
	}

	public static boolean confirmPackage(String trackingNumber) {
		// TODO: using @trackingNumber to confirm package
		boolean success = false;
		if (success)
			return true;
		else
			return false;
	}

	public static void checkPackage(String trackingNumber, String login, boolean isSender, boolean onGoing) {
		if (trackingNumber != null) {
			// TODO: Using @trackingNumber find package info
			System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender", "Receiver",
					"Current Location", "Status"));
		}
		if (login != null) {
			if (isSender) {
				if (!onGoing) {
					// TODO: using @loging who is a sender to find package info
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
				if (onGoing) {
					// TODO: using @loging who is a sender to find package info which is in transit
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
					// TODO: using @loging who is a receiver to find package info which is in
					// transit
					System.out.println(String.format("%-20s %-10s %-10s %-10s %-10", "Tracking Number", "Sender",
							"Receiver", "Current Location", "Status"));
				}
			}
		}
	}

	public static void estimatePackage(String trackingNumber) {
		int eachLocationTime = 30; // Each stop 30 second
		int current = 0;
		int destination = 0;
		// TODO: Using @trackingNumber to get @current @destination
		int estimate = Math.abs(current - destination) * eachLocationTime + 30;// From last warehouse to final location.
		System.out.println("Your estimate deliver time is in " + estimate + " second.");
	}

	public static void getAllPackage() {
		System.out.println(String.format("%-20s %-10s %-10s %-20s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		// TODO: Get all package info
	}

	public static void editData() {
		// TODO
	}
}
