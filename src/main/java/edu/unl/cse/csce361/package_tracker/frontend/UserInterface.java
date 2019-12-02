package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterface {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		boolean programOn = true;
		while(programOn) {
			String inputMain;
			logic.printMainMenu();
			inputMain = scnr.nextLine();
			switch(inputMain) {
			case "1": //**ADMIN**
				adminMenu();
				break;
			case "2": //**USER**
				userMenu();
				break;
			case "3": //**VIP**

				break;
			case "4":
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
		String input;
		logic.printAdminMenu();
		input = scnr.nextLine();
		switch(input) {
		case "1":
			logic.getAllPackage();
			break;
		}
	}

	public static void userMenu() {
		logic.printUserMenu();
	}

	public static void vipMenu() {

	}
}
