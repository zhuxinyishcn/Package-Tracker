package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class logicFacade {
	private static logicFacade unique;
	
	private logicFacade() {}
	
	public static logicFacade getInstance() {
		if (unique == null) {
			unique = new logicFacade();
		}
		return unique;
	}
	
	public void printMainMenu() {
		Printer.printMainMenu();
	}
	
	public void printAdminMenu() {
		Printer.printAdminMenu();
	}
	
	public void printUserMenu() {
		Printer.printUserMenu();
	}
	
	public void printAskUserName() {
		Printer.printAskUserName();
	}

	public void printInvalid() {
		Printer.printInvalid();
	}
	
	public void printExit() {
		Printer.printExit();
	}
	
	public void printVIPMenu() {
		Printer.printVIPMenu();
	}
	
	public void printRegisterMenu(int count) {
		Printer.printRegisterMenu(count);
	}
	
	public void register(String login, String realName, String warehouseID, String street, String city, String zipCode) {
		RegisterLogic.register(login, realName, warehouseID, street, city, zipCode);
	}
	
	public void getAllPackage() {
		PackageLogic.getAllPackage();
	}
	
}
