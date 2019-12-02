package edu.unl.cse.csce361.package_tracker.frontend;


public class Printer {

	public static void printMainMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("1. Log in as admin.");
		System.out.println("2. Log in as user.");
		System.out.println("3. Log in as VIP user.");
		System.out.println("4. Register.");
		System.out.println("5. Exit");
	}

	public static void printAdminMenu() {
		System.out.println("You're options are as follows:");
		System.out.println("1. Check ");
	}

	public static void printUserMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("Enter your user name:");
	}

	public static void printVIPMenu() {
		System.out.println("Enter your name:");
	}

	public static void printRegisterMenu(int count) {
		System.out.println("Please enter your Username, Warehouse ID, Street, City, Zip Code");
		switch(count) {
		case 1:
			System.out.println("Please enter your User Name.");
			break;
		case 2:
			
			System.out.println("Please enter Warehouse ID.");
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
}
