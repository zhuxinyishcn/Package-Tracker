package edu.unl.cse.csce361.package_tracker.frontend;

import java.util.Scanner;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

public class UserInterface {
	private static logicFacade logic = logicFacade.getInstance();
	
	public static void main(String[] args) {
		boolean programOn = true;
		while(programOn) {
			String input;
			Scanner scan = new Scanner(System.in);
			logic.printMainMenu();
			input = scan.nextLine();
			switch(input) {
			case "1": //*********************************** login as admin
				
				break;
			case "2": //*********************************** login as user
				
				break;
			case "3": //*********************************** login as VIP user
				
				break;
			case "4":
				programOn = false;
				scan.close();
				break;
			default:
				logic.printInvalid();
			}
		}
	}
}
