package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.*;

import edu.unl.cse.csce361.package_tracker.backend.Sender;
import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class Menu implements Observer {
	private static final logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);
	private static String userName = UserInterFace.getUserName();
	private static Sender userFile = UserInterFace.getSender();

	public static String getUserName() {
		return userName;
	}

	private static Sender getSender() {
		return userFile;
	}

	public static void setUserName(String userName) {
		Menu.userName = userName;
	}

	public static void adminMenu() {
		boolean programOn = true;
		while (programOn) {
			String userSelect;
			String user;
			String street;
			String city;
			String zipCode;
			String inputTracking;
			String warehouseID;
			Printer.printAdminMenu();
			userSelect = scnr.nextLine();
			switch (userSelect) {
			case "1":
				logic.getAllPackage();
				break;
			case "2":
				// TODO: Confirm package
				break;
			case "3":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
				Printer.printAskStreet();
				street = scnr.nextLine();
				Printer.printAskCity();
				city = scnr.nextLine();
				Printer.printAskZipCode();
				zipCode = scnr.nextLine();
				logic.editReceiver(inputTracking, street, city, zipCode);
				break;
			case "4":
				Printer.printAskUserName();
				user = scnr.nextLine();
				Printer.printAskStreet();
				street = scnr.nextLine();
				Printer.printAskCity();
				city = scnr.nextLine();
				Printer.printAskZipCode();
				zipCode = scnr.nextLine();
				logic.editInfo(user, street, city, zipCode);
				break;
			case "5":
				editPackage();
				break;
			case "6":
				// TODO: Fuzzy search
				break;
			case "7":
				Printer.printAskWarehouse();
				warehouseID = scnr.nextLine();
				logic.callDrone(warehouseID);
			case "8":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void editPackage() {
		String input1;
		String street;
		String city;
		String zipCode;
		String inputTracking;
		String status;
		int priorityID;
		int currentLocation;
		Printer.printAskTracking();
		inputTracking = scnr.nextLine();
		Printer.printEditPackage();
		input1 = scnr.nextLine();
		switch (input1) {
		case "1":
			Printer.printWarehouse();
			Printer.printAskInput();
			try {
				currentLocation = scnr.nextInt();
				scnr.nextLine();
				logic.editCurrentLocation(inputTracking, currentLocation);
			} catch (InputMismatchException e) {
				Printer.printInvalid();
			}
			break;
		case "2":
			Printer.printAskInput();
			try {
				priorityID = scnr.nextInt();
				scnr.nextLine();
				logic.editPriorityID(inputTracking, priorityID);
			} catch (InputMismatchException e) {
				Printer.printInvalid();
			}
			break;
		case "3":
			Printer.printAskInput();
			status = scnr.nextLine();
			logic.editStatus(inputTracking, status);
			break;
		case "4":
			Printer.printAskStreet();
			street = scnr.nextLine();
			Printer.printAskCity();
			city = scnr.nextLine();
			Printer.printAskZipCode();
			zipCode = scnr.nextLine();
			logic.editReceiver(inputTracking, street, city, zipCode);
			break;
		case "5":
			break;
		default:
			Printer.printInvalid();
		}
	}

	public static void userMenu() {
		boolean programOn = true;
		while (programOn) {
			String input;
			String receiverName;
			String street;
			String city;
			String zipCode;
			String inputTracking;
			String inputCheck;
			logic.printUserMenu();
			input = scnr.nextLine();
			switch (input) {
			case "1":
				if (logic.checkAvilability()) {
					Printer.printAskreceiverName();
					receiverName = scnr.nextLine();
					Printer.printAskStreet();
					street = scnr.nextLine();
					Printer.printAskCity();
					city = scnr.nextLine();
					Printer.printAskZipCode();
					zipCode = scnr.nextLine();
					logic.newPackage(userName, receiverName, street, city, zipCode);
				} else {
					Printer.printNotAvilable();
				}
				break;
			case "2":
				// TODO: Confirm package
				break;
			case "3":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.returnPackage(inputTracking);
				break;
			case "4":
				Printer.printAskStreet();
				street = scnr.nextLine();
				Printer.printAskCity();
				city = scnr.nextLine();
				Printer.printAskZipCode();
				zipCode = scnr.nextLine();
				logic.editInfo(userName, street, city, zipCode);
				break;
			case "5":
				logic.printCheckPackage();
				inputCheck = scnr.nextLine();
				switch (inputCheck) {
				case "1":
					logic.printAskTracking();
					inputTracking = scnr.nextLine();
					logic.checkPackageByTrackingNumber(inputTracking);
					break;
				case "2":
					logic.checkPackageByUserName(userName);
					break;
				case "3":
					break;
				default:
					logic.printInvalid();
				}
				break;
			case "6":
				logic.becomeVIP(userName);
				break;
			case "7":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.cancelPackage(inputTracking);
				break;
			case "8":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.holdAtWarehouse(inputTracking);
				break;
			case "9":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.estimatePackage(inputTracking);
				break;
			case "10":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void vipMenu() {
		boolean programOn = true;
		while (programOn) {
			String input;
			String receiverName;
			String street;
			String city;
			String zipCode;
			String inputTracking;
			String inputCheck;
			logic.printVIPMenu();
			input = scnr.nextLine();
			switch (input) {
			case "1":
				if (logic.checkAvilability()) {
					Printer.printAskreceiverName();
					receiverName = scnr.nextLine();
					Printer.printAskStreet();
					street = scnr.nextLine();
					Printer.printAskCity();
					city = scnr.nextLine();
					Printer.printAskZipCode();
					zipCode = scnr.nextLine();
					logic.newPackage(userName, receiverName, street, city, zipCode);
				} else {
					Printer.printNotAvilable();
				}
				break;
			case "2":
				// TODO: Confirm package
				break;
			case "3":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.returnPackage(inputTracking);
				break;
			case "4":
				Printer.printAskStreet();
				street = scnr.nextLine();
				Printer.printAskCity();
				city = scnr.nextLine();
				Printer.printAskZipCode();
				zipCode = scnr.nextLine();
				logic.editInfo(userName, street, city, zipCode);
				break;
			case "5":
				logic.printCheckPackage();
				inputCheck = scnr.nextLine();
				switch (inputCheck) {
				case "1":
					logic.printAskTracking();
					inputTracking = scnr.nextLine();
					logic.checkPackageByTrackingNumber(inputTracking);
					break;
				case "2":
					logic.checkPackageByUserName(userName);
					break;
				case "3":
					break;
				default:
					logic.printInvalid();
				}
				break;
			case "6":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.cancelPackage(inputTracking);
				break;
			case "7":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.holdAtWarehouse(inputTracking);
				break;
			case "8":
				logic.printAskTracking();
				inputTracking = scnr.nextLine();
				logic.estimatePackage(inputTracking);
				break;
			case "9":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
				Printer.printAskStreet();
				street = scnr.nextLine();
				Printer.printAskCity();
				city = scnr.nextLine();
				Printer.printAskZipCode();
				zipCode = scnr.nextLine();
				logic.editReceiver(inputTracking, street, city, zipCode);
				break;
			case "10":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void register() {
		String user;
		String street;
		String city;
		String zipCode;
		String realName;
		Printer.printAskUserName();
		user = scnr.nextLine();
		Printer.printAskRealName();
		realName = scnr.nextLine();
		Printer.printAskStreet();
		street = scnr.nextLine();
		Printer.printAskCity();
		city = scnr.nextLine();
		Printer.printAskZipCode();
		zipCode = scnr.nextLine();
		logic.register(user, realName, street, city, zipCode);

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
