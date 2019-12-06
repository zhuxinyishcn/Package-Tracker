package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterFace {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);
	private static String userName;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		UserInterFace.userName = userName;
	}

	public static void main(String[] args) {
		logic.addWarehouse();
		boolean programOn = true;
		while (programOn) {
			String inputMain;
			Printer.printMainMenu();
			inputMain = scnr.nextLine();
			switch (inputMain) {
			case "1": // **ADMIN**
				Menu.adminMenu();
				break;
			case "2": // **USER**
				Printer.printAskUserName();
				userName = scnr.nextLine();
				if(logic.checkVip(userName)) {
					Menu.vipMenu();
				}else {
					Menu.userMenu();
				}
				break;
			case "3":
				programOn = false;
				Printer.printExit();
				break;
			default:
				logic.printInvalid();
			}
		}
	}
}
