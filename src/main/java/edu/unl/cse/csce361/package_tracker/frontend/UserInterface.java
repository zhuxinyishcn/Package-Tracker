package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterface {
	private static logicFacade logic = logicFacade.getInstance();
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		boolean programOn = true;
		while(programOn) {
			String inputMain;
			String inputLogin = "";
			String warehouseID = "";
			String inputStreet = "";
			String inputCity = "";
			String inputZip = "";
			logic.printMainMenu();
			inputMain = scan.nextLine();
			switch(inputMain) {
			case "1": //**ADMIN**
				
				break;
			case "2": //**USER**
				userMenu();
				break;
			case "3": //**VIP**

				break;
			case "4":
				logic.register(inputLogin, warehouseID, inputStreet, inputCity, inputZip);
				break;
			case "5":
				programOn = false;
				logic.printExit();
				scan.close();
				break;
			default:
				logic.printInvalid();
			}
		}
	}

	public static void adminMenu() {

	}

	public static void userMenu() {
		String inputUser;
		logic.printUserMenu();
	}

	public static void vipMenu() {

	}
}
