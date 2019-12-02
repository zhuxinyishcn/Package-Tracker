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
	
	public static void printDeactivate(String userName) {
		System.out.println("Confirm remove all data for user: " + userName + "(Y/N)");
	}

	public static void printWarehouse() {
		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "1", "Seward", "Weller Hall, 800 N Columbia Ave, Seward, NE 68434"));
		System.out.println(String.format("%-5s %-30s %-50s", "2", "Milford", "923 238th Rd, Milford, NE 68405"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "3", "Lincoln 112st", "790-926 NW 112th St, Lincoln, NE 68528"));
		System.out.println(String.format("%-5s %-30s %-50s", "4", "Lincoln Hub", "2701 O St, Lincoln, NE 68510"));
		System.out.println(String.format("%-5s %-30s %-50s", "5", "O and 84",
				"Weller Hall, 800 N Columbia Ave, Seward, NE 68434"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "6", "O and Nebraska", "8525 Andermatt Drive, Lincoln, NE 68526"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "7", "I80 and 154 st", "14541 Castlewood St, Waverly, NE 68462"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "8", "I80 and 250 st", "14599 250th St, Greenwood, NE 68366"));
		System.out.println(String.format("%-5s %-30s %-50s", "9", "I80 and Nebraska Crossing",
				"21209 Nebraska Crossing Dr, Gretna, NE 68028"));
		System.out.println(
				String.format("%-5s %-30s %-50s", "10", "I80 and 118th St", "6271 S 118th St, Omaha, NE 68137"));
		System.out.println(String.format("%-5s %-30s %-50s", "11", "Omaha Hub", "3110 Farnam St, Omaha, NE 68131"));
		System.out
				.println(String.format("%-5s %-30s %-50s", "12", "Missouri River", "4501 Abbott Dr, Omaha, NE 68110"));
	}
}
