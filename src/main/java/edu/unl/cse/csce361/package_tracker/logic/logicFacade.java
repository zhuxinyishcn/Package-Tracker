package edu.unl.cse.csce361.package_tracker.logic;

import java.util.ArrayList;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class logicFacade {
	private static logicFacade unique;

	private logicFacade() {
	}

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
	
	public void PrintChangeDestitation(int count) {
		Printer.PrintChangeDestitation(count);
	}
	
	public void changeDestitation(String trackingNumber, String destitationLogin){
		AdminLogic.changeDestitation(trackingNumber, destitationLogin);
	}

	public void printIsSender() {
		Printer.printIsSender();
	}
	
	public void editInfo(String isSender, String userName, String name, String street, String city, String zip,
			String warehouseID) {
		Logic.editInfo(isSender, userName, name, street, city, zip, warehouseID);
	}

	public void register(String isSender, String login, String realName, String warehouseID, String street, String city,
			String zipCode) {
		Logic.register(isSender, login, realName, warehouseID, street, city, zipCode);
	}
	
	public void editPackage(String trackingNumber, String currentLocation, String priorityID,
			String shippingTime, String status, String receiver, String sender) {
		AdminLogic.editPackage(trackingNumber, currentLocation, priorityID, shippingTime, status, receiver, sender);
	}

	public void printEditPackage(int count) {
		Printer.printEditPackage(count);;
	}

	public void getAllPackage() {
		AdminLogic.getAllPackage();
	}
	public void addWarehouse() {
		ShippingLogic.addWarehouse();
	}

	public ArrayList<String> getWarehouse() {
		return ShippingLogic.warehouse;
	}
}
