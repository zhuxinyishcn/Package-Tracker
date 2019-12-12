package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.List;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

import java.time.LocalDateTime;
import java.util.Set;

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
		System.out.println("2. Confirm package.");
		System.out.println("3. Change destination.");
		System.out.println("4. Edit user data.");
		System.out.println("5. Edit package.");
		System.out.println("6. Fuzzy search by tracking number.");
		System.out.println("7. Back to main menu.");
	}

	public static void printUserMenu() {
		System.out.println("Welcome, user options are as follows:");
		System.out.println("1. Send your package.(Destination address)");
		System.out.println("2. Confirm package.");
		System.out.println("3. Retuen package.");
		System.out.println("4. Edit personal data."); // address only
		System.out.println("5. Check your package.");
		System.out.println("6. Upgrade to VIP.");
		System.out.println("7. Cancel package.");
		System.out.println("8. Hold at Warehouse.");
		System.out.println("9. Estimate dilivery time.");
		System.out.println("10. Back to main menu.");
	}

	public static void printDestination() {
		System.out.println("Enter your destination:");
	}

	public static void printVIPMenu() {
		System.out.println("Welcome, VIP options are as follows:");
		System.out.println("1. Send priority package.(Destination address)");
		System.out.println("2. Confirm package.");
		System.out.println("3. Return package.");
		System.out.println("4. Edit personal data.");
		System.out.println("5. Check your package.");
		System.out.println("6. Cancel package.");
		System.out.println("7. Hold at Warehouse.");
		System.out.println("8. Estimate dilivery time.");
		System.out.println("9. Change destination.");
		System.out.println("10. Back to main menu.");
	}

	public static void printAskreceiverName() {
		System.out.println("Enter the receiver real name:");
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
		for (int i = 0; i <= logic.getWarehouse().size() - 1; i++) {
			System.out.println(logic.getWarehouse().get(i).toString());
		}
	}

	public static void printNotAvilable() {
		System.out.println("We do not have abilable drone. Please try again later.");
	}

	/*
	 * ^ |FRONTEND PRINTER | LOGICEND PRINTER v
	 *
	 */
	public static void printlogicChangeDestitation(String trackingNumber, String destitationLogin) {
		System.out.println("You have successfully set destitation of tracking number:" + trackingNumber + " to "
				+ destitationLogin);
	}

	public static void printLogicPackageByTrackingNumber(Package info) {
		printPacakge(info);
	}

	public static void printLogicPackageByUserName(Set<Package> result) {
		for (Package p : result) {
			printPacakge(p);
		}
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

	public static void printLogicEstimateTime(LocalDateTime timeMinutes) {
		System.out.println("Your estimate deliver time is in " + timeMinutes.getDayOfWeek() + "  " + timeMinutes);
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
		for (Package p : result) {
			printPacakge(p);
		}
	}

	public static void printLogicUserFound(String userName) {
		System.out.println("User Name: " + userName + " already been used, please try the another one");
	}

	public static void printLogicAddressNotFound() {
		System.err.println("Your address can not be resolved, Please type in your actual address.");
	}

	public static void printPacakge(Package p) {
		System.out.printf(
				"Tracking Number:%-20s\nSender:%-10s\nReceiver:%-10s\nCurrent Location:%-20s\nStatus:%-10s\nstart Location:%-10s\nend Location:%10s\nestimated time:%s\n\n",
				p.getTrackingNumber(), p.getSender().getName(), p.getReceiver().getName(),
				logic.getWarehouse().get(p.getCurrentLocation() - 1).getName(), p.getStatus(),
				p.getSender().getAddress(), p.getReceiver().getAddress(), p.getEstimateTime());
	}

	public static void printLogicNotSender(String trackingNumber) {
		System.err.println("Tracking number: " + trackingNumber
				+ " do not belong to you, Please double check your tracking number or contact admin for support.");
	}

	public static void printLogicLoading() {
		System.out.println("Loading................");
	}

}
