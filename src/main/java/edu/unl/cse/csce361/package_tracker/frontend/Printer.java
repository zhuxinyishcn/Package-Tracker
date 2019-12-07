package edu.unl.cse.csce361.package_tracker.frontend;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class Printer {
	private static logicFacade logic = logicFacade.getInstance();
	
	public static void printMainMenu() {
		System.out.println("Welcome, this is main menu:");
		System.out.println("1. Log in as admin.");
		System.out.println("2. Log in as user.");
		System.out.println("3. Register.");
		System.out.println("4. Exit");
	}

	public static void printAskUserName() {
		System.out.println("Enter your user name:");
	}
	
	public static void printAskTracking() {
		System.out.println("Enter your tracking number:");
	}

	public static void printAskInput() {
		System.out.println("Please enter:");
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
		System.out.println("1. Send your package.(Destination address)");
		System.out.println("2. Retuen package.");
		System.out.println("3. Edit personal data."); //address only
		System.out.println("4. Check your package.(Tracking# or User Name)");
		System.out.println("5. Upgrade to VIP.");
		System.out.println("6. Cancel package.");
		System.out.println("7. Hold at Warehouse.");
		System.out.println("8. Estimate dilivery time.");
		System.out.println("9. Back to main menu.");
	}

//	public static void printSendPackage(int count) {
//		switch(count) {
//		case 1:
//			System.out.println("What is your user name?");
//			break;
//		case 2:
//			System.out.println("What is your destination?");
//			break;
//		}
//	}
	
	public static void printDestination() {
		System.out.println("Enter your destination:");
	}
	
	public static void printVIPMenu() {
		System.out.println("Welcome, VIP options are as follows:");
		System.out.println("1. Send priority package.(Destination address)");
		System.out.println("2. Return package.");
		System.out.println("3. Edit personal data.");
		System.out.println("4. Check your package.(Tracking# or User Name)");
		System.out.println("5. Cancel package.");
		System.out.println("6. Hold at Warehouse.");
		System.out.println("7. Estimate dilivery time.");
		System.out.println("8. Change destination.");
		System.out.println("9. Back to main menu.");
	}
	
	public static void printAskRealName() {
		System.out.println("Enter your real name:");
	}
	
	public static void printAskStreet() {
		System.out.println("Enter your street:");
	}
	
	public static void printAskCity() {
		System.out.println("Enter your city:");
	}
	
	public static void printAskZipCode() {
		System.out.println("Enyer your Zip code:");
	}
	
	public static void printAskRegister() {
		System.out.println("You should register our system first.");
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
	
	public static void printEditPackage() {
		System.out.println("Please select a data you would like to edit:");
		System.out.println("1. Edit current location.");
		System.out.println("2. Edit priority ID.");
		System.out.println("3. Edit package status.");
		System.out.println("4. Edit receiver.");
		System.out.println("5. Back to administration menu.");
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
		for (int i = 0; i <= logic.getWarehouse().size()-1; i++) {
			System.out.println(logic.getWarehouse().get(i).toString());
		}
	}

}
