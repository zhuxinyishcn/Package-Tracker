package edu.unl.cse.csce361.package_tracker.logic;

public class AdminLogic {
	public static void changeDestitation(String trackingNumber, String destitationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destitation
	}

	public static void getAllPackage() {
		System.out.println(String.format("%-20s %-10s %-10s %-20s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		// TODO: Get all package info
	}

	public static void editPackage() {
		// TODO:
	}

	public static boolean confirmPackage(String trackingNumber) {
		// TODO: using @trackingNumber to confirm package
		boolean success = false;
		if (success)
			return true;
		else
			return false;
	}
}
