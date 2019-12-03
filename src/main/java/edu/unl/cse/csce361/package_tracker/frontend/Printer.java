package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class Printer {
	private static logicFacade logic = logicFacade.getInstance();

	public static void printMainMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("1. Log in as admin.");
		System.out.println("2. Log in as user.");
		System.out.println("3. Log in as VIP user.");
		System.out.println("4. Exit");
	}

	public static void printAdminMenu() {
		System.out.println("You're options are as follows:");
		System.out.println("1. Show all package. ");
	}

	public static void printUserMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("Enter your user name:");
	}

	public static void printVIPMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("Enter your name:");
	}

	public static void printRegisterMenu(int count) {
		switch (count) {
		case 1:
			System.out.println("Please enter your User Name.");
			break;
		case 2:
			printWarehouse();
			System.out.println("Please select the closest Warehouse ID.");
			break;
		case 3:
			System.out.println("Please enter Street Name.");
			break;
		case 4:
			System.out.println("Please enter City Name.");
			break;
		case 5:
			System.out.println("Please enter Zip Code.");
			break;
		}
	}

	public static void printExit() {
		System.out.println("Thank you for using our program. Terminating");
	}

	public static void printInvalid() {
		System.out.println("Error: invaild input");
	}

	public static void printDeactivate(String userName) {
		System.out.println("Confirm remove all data for user: " + userName + "(Y/N)");
	}

	public static void printWarehouse() {
		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
		for (int i = 0; i <= logic.getWarehouse().size(); i++) {
			System.out.println(logic.getWarehouse().get(i).toString());
		}

	}
}
