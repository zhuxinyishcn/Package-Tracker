package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;
import java.util.List;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.logic.ShippingLogic;
import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class Printer {
	private static logicFacade logic = logicFacade.getInstance();

	public static void printMainMenu() {
		System.out.println("Welcome, this is main menu:");
		System.out.println("1. Log in as admin.");
		System.out.println("2. Log in as user.");
		System.out.println("3. Exit");
	}

	public static void printAskUserName() {
		System.out.println("Enter your user name:");
	}

	public static void printAskTracking() {
		System.out.println("Enter your tracking number:");
	}

	public static void printAdminMenu() {
		System.out.println("Welcome, administration options are as follows:");
		System.out.println("1. Show all package.");
		System.out.println("2. Change destination.");
		System.out.println("3. Edit user data.");
		System.out.println("4. Edit package.");
		System.out.println("5. Back to main menu.");
	}

	public static void printUserMenu() {
		System.out.println("Welcome, user options are as follows:");
		System.out.println("1. Send your package.");
		System.out.println("2. Retuen package.");
		System.out.println("3. Edit personal data."); // address only
		System.out.println("4. Check your package.(Tracking# or User Name)");
		System.out.println("5. Upgrade to VIP.");
		System.out.println("6. Cancel package.");
		System.out.println("7. Hold at Warehouse.");
		System.out.println("8. Estimate dilivery time.");
		System.out.println("9. Back to main menu.");
	}

	public static void printSendPackage(int count) {
		switch (count) {
		case 1:
			System.out.println("What is your user name?");
			break;
		case 2:
			System.out.println("What is your destination?");
			break;
		}
	}

	public static void printDestination() {
		System.out.println("Enter your destination:");
	}

	public static void printVIPMenu() {
		System.out.println("Welcome, VIP options are as follows:");
		System.out.println("1. Send priority package.");
		System.out.println("2. Retuen package.");
		System.out.println("3. Edit personal data.");
		System.out.println("4. Check your package.(Tracking# or User Name)");
		System.out.println("5. Upgrade to VIP.");
		System.out.println("6. Cancel package.");
		System.out.println("7. Hold at Warehouse.");
		System.out.println("8. Estimate dilivery time.");
		System.out.println("9. Change destination.");
		System.out.println("10. Back to main menu.");
	}

//	public static void PrintChangeDestination(int count) {
//		switch(count) {
//		case 1:
//			System.out.println("What is your tracking number?");
//			break;
//		case 2:
//			System.out.println("What is your new destination user name?");
//			break;
//		}
//	}

//	public static void printRegisterMenu(int count) {
//		switch (count) {
//		case 1:
//			System.out.println("Are you a sender?(y/n)");
//			break;
//		case 2:
//			System.out.println("Please enter your User Name.");
//			break;
//		case 3:
//			System.out.println("Please enter your Real Name");
//			break;
//		case 4:
//			printWarehouse();
//			System.out.println("Please select the closest Warehouse ID.");
//			break;
//		case 5:
//			System.out.println("Please enter Street Name.");
//			break;
//		case 6:
//			System.out.println("Please enter City Name.");
//			break;
//		case 7:
//			System.out.println("Please enter Zip Code.");
//			break;
//		}
//	}

	public static void printEditPackage(int count) {
		switch (count) {
		case 1:
			System.out.println("What is your tracking number?");
			break;
		case 2:
			System.out.println("What is new current location?");
			printWarehouse();
			break;
		case 3:
			System.out.println("What is new priority ID?");
			break;
		case 4:
			System.out.println("What is new shipping time?");
			break;
		case 5:
			System.out.println("What is new status?");
			break;
		case 6:
			System.out.println("What is new receiver?");
			break;
		case 7:
			System.out.println("What is new sender?");
			break;
		}
	}

	public static void printCheckPackage() {
		System.out.println("1. Check by tracking number.");
		System.out.println("2. Check by user name.");
		System.out.println("3. Back to user menu.");
	}

	public static void printExit() {
		System.out.println("Thank you. Terminating...");
	}

	public static void printInvalid() {
		System.out.println("Error: invaild input");
	}

	public static void printDeactivate(String userName) {
		System.out.println("Confirm remove all data for user: " + userName + "(Y/N)");
	}

	public static void printIsSender() {
		System.out.println("Are you a sender?(y/n)");
	}

	public static void printWarehouse() {
		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
		for (int i = 0; i <= logic.getWarehouse().size() - 1; i++) {
			System.out.println(logic.getWarehouse().get(i).toString());
		}
	}

	/*
	 * ^ |FRONTEND PRINTER | LOGICEND PRINTER v
	 * 
	 */
	public static void printlogicChangeDestitation(String trackingNumber, String destitationLogin) {
		System.out.println("You have successfully set destitation of tracking number:" + trackingNumber + " to "
				+ destitationLogin);
	}

	public static void printLogicPackageByTrackingNumber(String info) {
		System.out.println(String.format("%-20s %-10s %-10s %-10s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		System.out.println(info);
	}

	public static void printLogicPackageByUserName(ArrayList<String> result) {
		System.out.println(String.format("%-20s %-10s %-10s %-10s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		System.out.println(result.toString());
	}

	public static void printLogicNewPackage(String trackingNumber) {
		System.out.println("You have successfully create a new package, your tacking number is: " + trackingNumber);
	}

	public static void printLogicCencelPackage(String trackingNumber) {
		System.out.println("Your package: " + trackingNumber + " had been canceled. Your package will be destoryed.");
	}

	public static void printLogicHoldWarehouse(int holdWarehouseID) {
		System.out.println("Your package will be hold at: ");
		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
		System.out.println(logic.getWarehouse().get(holdWarehouseID - 1).toString());
	}

	public static void printLogicEstimateTime(int timeMinutes) {
		System.out.println("Your estimate deliver time is in " + timeMinutes + " minutes.");
	}

	public static void printLogicArriveNotify(String trackingNumber) {
		System.out.println("Your package: " + trackingNumber + " has arrived. Please go to login to comfirm received.");
	}

	public static void printLogicArrivePackage(String trackingNumner) {
		System.out.println("Your package: " + trackingNumner + " has arrived. Please confirm receive.");
	}

	public static void printLogicErrAddress() {
		System.err.println(
				"Street should less than 100 charactor, city should less than 50 charactor, zip code should less than 10 charactor");
	}

	public static void printErrInput(String name, String length) {
		System.err.println(name + " should less than " + length + " charactor.");
	}

	public static void printLogicErrAPIKeyMissing() {
		System.err.println("API key file not found.");
	}

	public static void printLogicErrNotInServiceRange(double distance) {
		System.err.println("You are not in services range. You are " + distance
				+ " miles from the closest warehouse. The maximum distance for dilvery is 10 miles from warehouse.");
	}

	public static void printLogicRequestSuccess(String request) {
		System.out.println("We have successfully process your request to " + request + ".");
	}

	public static void printLogicAllPackage(List<Package> result) {
		System.out.println(String.format("%-20s %-10s %-10s %-20s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		int i = 0;
		for (Package p : result) {
			System.out.printf("Tracking Number:%-20s\nSender:%-10s\n Receiver:%-10s\n %-20s %-10s\n\n",
					p.getTrackingNumber(), p.getSender().getName(), p.getReceiver().getName(),
					ShippingLogic.warehouse.get(i), p.getStatus());
			i++;
		}
	}
}
