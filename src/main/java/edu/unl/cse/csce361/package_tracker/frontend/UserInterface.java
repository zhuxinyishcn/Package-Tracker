package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterface {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
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
				ArrayList<String> destitation = new ArrayList<String>();
				for (int i = 1; i <= 2; i++) {
					logic.PrintChangeDestitation(i);
					inputData = scnr.nextLine();
					destitation.add(inputData);
				}
				logic.changeDestitation(destitation.get(0), destitation.get(1));
				break;
			case "3":
				ArrayList<String> registerList = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					logic.printRegisterMenu(i);
					inputData = scnr.nextLine();
					if (i == 1) {
						if (inputData.equalsIgnoreCase("Y") == false && inputData.equalsIgnoreCase("N") == false) {
							i = 0;
						} else
							registerList.add(inputData);
					} else
						if(inputData.isEmpty()) {
							inputData = null;
							registerList.add(inputData);
						}else {
							registerList.add(inputData);
						}
				}
				logic.editInfo(registerList.get(0), registerList.get(1), registerList.get(2), registerList.get(3),
						registerList.get(4), registerList.get(5), registerList.get(6));
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
		String input;
		String userName;
		logic.printAskUserName();
		userName = scnr.nextLine();
		logic.printUserMenu();
	}

	public static void vipMenu() {
		logic.printVIPMenu();
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
