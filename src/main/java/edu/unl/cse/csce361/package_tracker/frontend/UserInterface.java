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
			if (input.compareTo("1") == 0) {//*********************************** login as admin
				
			}else if (input.compareTo("2") == 0) {//*********************************** login as user
				
			}else if (input.compareTo("3") == 0) {//*********************************** login as VIP user
				
			}else if (input.compareTo("4") == 0) {//*********************************** EXIT
				programOn = false;
				scan.close();
			}else {
				logic.printInvalid();
			}
		}
	}
}
