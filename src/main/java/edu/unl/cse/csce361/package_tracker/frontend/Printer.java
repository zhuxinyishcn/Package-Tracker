package edu.unl.cse.csce361.package_tracker.frontend;


public class Printer {
	
	public static void printMainMenu() {
		System.out.println("Welcome, you're options are as follows:");
		System.out.println("1. Log in as admin.");
		System.out.println("2. Log in as user.");
		System.out.println("3. Log in as VIP user.");
		System.out.println("4. Exit");
	}

	public static void printAdminMenu() {
		System.out.println("You're options are as follows:");
		System.out.println("1. Check ");
	}

	public static void printUserMenu() {
		System.out.println("you're options are as follows:");
	}


	public static void printVIPMenu() {
		System.out.println("Enter your name:");
	}

	public static void printExit() {
		System.out.println("Thank you for using our program. Terminating");
	}
	
	public static void printInvalid() {
		System.out.println("Error: invaild input");
	}
}
