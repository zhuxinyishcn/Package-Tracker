package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class Menu {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);
	private static String userName = UserInterFace.getUserName();

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Menu.userName = userName;
	}

	public static void adminMenu() {
		boolean programOn = true;
		while(programOn) {
			String input;
			String user;
			String street;
			String city;
			String zipCode;
			String inputTracking;
			String inputDestination;
			Printer.printAdminMenu();
			input = scnr.nextLine();
			switch (input) {
			case "1":
				logic.getAllPackage();
				break;
			case "2":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
				Printer.printDestination();
				inputDestination = scnr.nextLine();
				logic.changeDestination(inputTracking, inputDestination);
				break;
			case "3":
				Printer.printAskTracking();
				inputTracking = scnr.nextLine();
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
			case "4":
//				ArrayList<String> packageList = new ArrayList<String>();
//				for (int i = 1; i <= 7; i++) {
//					logic.printEditPackage(i);
//					inputData = scnr.nextLine();
//					if(inputData.isEmpty()) {
//						inputData = null;
//						packageList.add(inputData);
//					}else {
//						packageList.add(inputData);
//					}
//				}
				break;
			case "5":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void userMenu() {
		boolean programOn = true;
		logic.printAskUserName();
		userName = scnr.nextLine();
		while(programOn) {
			String input;
			String inputCheck;
			String inputData;
			logic.printUserMenu();
			input = scnr.nextLine();
			switch(input) {
			case "1":
				ArrayList<String> sendPackage = new ArrayList<String>();
				for (int i = 1; i <= 2; i++) {
					logic.printSendPackage(i);
					inputData = scnr.nextLine();
					sendPackage.add(inputData);
				}
				logic.newPackage(sendPackage.get(0), sendPackage.get(1));
				break;
			case "2":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.returnPackage(inputData);
				break;
			case "3":
				ArrayList<String> editList = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					//logic.printRegisterMenu(i);
					inputData = scnr.nextLine();
					if (i == 1) {
						if (inputData.equalsIgnoreCase("Y") == false && inputData.equalsIgnoreCase("N") == false) {
							i = 0;
						} else
							editList.add(inputData);
					} else
						if(inputData.isEmpty()) {
							inputData = null;
							editList.add(inputData);
						}else {
							editList.add(inputData);
						}
				}
				logic.editInfo(editList.get(0), editList.get(1), editList.get(2), editList.get(3),
						editList.get(4), editList.get(5), editList.get(6));
				break;
			case "4":
				logic.printCheckPackage();
				inputCheck = scnr.nextLine();
				boolean checkOn = true;
				while(checkOn) {
					switch(inputCheck) {
					case "1":
						logic.printAskTracking();
						inputData = scnr.nextLine();
						logic.checkPackage(inputData, null, false, false); // need to be fix
						break;
					case "2":
						logic.printAskUserName();
						inputData = scnr.nextLine();
						logic.checkPackage(null, inputData, false, false); // need to be fix
						break;
					case "3":
						checkOn = false;
						break;
					default:
						logic.printInvalid();
					}
				}
				break;
			case "5":
				logic.printAskUserName();
				inputData = scnr.nextLine();
				logic.becomeVIP(inputData);
				break;
			case "6":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.cancelPackage(inputData);
				break;
			case "7":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.holdAtWarehouse(inputData);
				break;
			case "8":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.estimatePackage(inputData);
				break;
			case "9":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void vipMenu() {
		boolean programOn = true;
		while(programOn) {
			String input;
			String inputCheck;
			String inputData;
			logic.printAskUserName();
			userName = scnr.nextLine();
			logic.printVIPMenu();
			input = scnr.nextLine();
			switch(input) {
			case "1":
				ArrayList<String> sendPackage = new ArrayList<String>();
				for (int i = 1; i <= 2; i++) {
					logic.printSendPackage(i);
					inputData = scnr.nextLine();
					sendPackage.add(inputData);
				}
				logic.newPackage(sendPackage.get(0), sendPackage.get(1));
				break;
			case "2":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.returnPackage(inputData);
				break;
			case "3":
				ArrayList<String> editList = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					//logic.printRegisterMenu(i);
					inputData = scnr.nextLine();
					if (i == 1) {
						if (inputData.equalsIgnoreCase("Y") == false && inputData.equalsIgnoreCase("N") == false) {
							i = 0;
						} else
							editList.add(inputData);
					} else
						if(inputData.isEmpty()) {
							inputData = null;
							editList.add(inputData);
						}else {
							editList.add(inputData);
						}
				}
				logic.editInfo(editList.get(0), editList.get(1), editList.get(2), editList.get(3),
						editList.get(4), editList.get(5), editList.get(6));
				break;
			case "4":
				logic.printCheckPackage();
				inputCheck = scnr.nextLine();
				boolean checkOn = true;
				while(checkOn) {
					switch(inputCheck) {
					case "1":
						logic.printAskTracking();
						inputData = scnr.nextLine();
						logic.checkPackage(inputData, null, false, false); // need to be fix
						break;
					case "2":
						logic.printAskUserName();
						inputData = scnr.nextLine();
						logic.checkPackage(null, inputData, false, false); // need to be fix
						break;
					case "3":
						checkOn = false;
						break;
					default:
						logic.printInvalid();
					}
				}
				break;
			case "5":
				logic.printAskUserName();
				inputData = scnr.nextLine();
				logic.becomeVIP(inputData);
				break;
			case "6":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.cancelPackage(inputData);
				break;
			case "7":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.holdAtWarehouse(inputData);
				break;
			case "8":
				logic.printAskTracking();
				inputData = scnr.nextLine();
				logic.estimatePackage(inputData);
				break;
			case "9":
				ArrayList<String> destination = new ArrayList<String>();
				for (int i = 1; i <= 2; i++) {
					logic.PrintChangeDestination(i);
					inputData = scnr.nextLine();
					destination.add(inputData);
				}
				logic.changeDestination(destination.get(0), destination.get(1));
			case "10":
				programOn = false;
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void register() {
		ArrayList<String> registerList = new ArrayList<String>();
		String inputData;
		for (int i = 1; i <= 7; i++) {
			//logic.printRegisterMenu(i);
			inputData = scnr.nextLine();
			if (i == 1) {
				if (inputData.equalsIgnoreCase("Y") == false && inputData.equalsIgnoreCase("N") == false) {
					i = 0;
				} else
					registerList.add(inputData);
			} else
				registerList.add(inputData);
		}
		logic.register(registerList.get(0), registerList.get(1), registerList.get(2), registerList.get(3),
				registerList.get(4), registerList.get(5), registerList.get(6));

	}

}
