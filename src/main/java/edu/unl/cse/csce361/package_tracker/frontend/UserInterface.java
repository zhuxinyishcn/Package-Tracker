package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterface {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		logic.addWarehouse();
		boolean programOn = true;
		while (programOn) {
			String inputMain;
			logic.printMainMenu();
			inputMain = scnr.nextLine();
			switch (inputMain) {
			case "1": // **ADMIN**
				adminMenu();
				break;
			case "2": // **USER**
				userMenu();
				break;
			case "3": // **VIP**
				vipMenu();
				break;
			case "4": // **REGISTER**
				register();
				break;
			case "5":
				programOn = false;
				logic.printExit();
				scnr.close();
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void adminMenu() {
		boolean programOn = true;
		while(programOn) {
			String input;
			String inputData = "";
			logic.printAdminMenu();
			input = scnr.nextLine();
			switch (input) {
			case "1":
				logic.getAllPackage();
				break;
			case "2":
				ArrayList<String> destination = new ArrayList<String>();
				for (int i = 1; i <= 2; i++) {
					logic.PrintChangeDestination(i);
					inputData = scnr.nextLine();
					destination.add(inputData);
				}
				logic.changeDestination(destination.get(0), destination.get(1));
				break;
			case "3":
				ArrayList<String> editList = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					logic.printRegisterMenu(i);
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
				ArrayList<String> packageList = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					logic.printEditPackage(i);
					inputData = scnr.nextLine();
					if(inputData.isEmpty()) {
						inputData = null;
						packageList.add(inputData);
					}else {
						packageList.add(inputData);
					}
				}
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
		while(programOn) {
			String userName;
			String input;
			String inputCheck;
			String inputData;
			logic.printAskUserName();
			userName = scnr.nextLine();
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
					logic.printRegisterMenu(i);
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
			String userName;
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
					logic.printRegisterMenu(i);
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
			logic.printRegisterMenu(i);
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
