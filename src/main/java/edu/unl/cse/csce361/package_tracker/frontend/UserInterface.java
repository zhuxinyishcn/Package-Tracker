package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.ArrayList;
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
				vipMenu();
				break;
			case "4": //**REGISTER**
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
		String input;
		logic.printAdminMenu();
		input = scnr.nextLine();
		switch(input) {
		case "1":
			//logic.getAllPackage();
			break;
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
		String input;
		for(int i = 1; i <= 6; i++) {
			logic.printRegisterMenu(i);
			input = scnr.nextLine();
			registerList.add(input);
		}
		//logic.register(registerList.get(0), registerList.get(1), registerList.get(2), registerList.get(3), registerList.get(4), registerList.get(5));
	}
}
