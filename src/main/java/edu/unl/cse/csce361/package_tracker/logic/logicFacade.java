package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.util.ArrayList;

public class logicFacade {
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
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

	public void PrintChangeDestination(int count) {
		Printer.PrintChangeDestination(count);
	}

	public void changeDestination(String trackingNumber, String destntationLogin) {
		AdminLogic.changeDestination(trackingNumber, destntationLogin);
	}

	public void changeDestitation(String trackingNumber, String destitationLogin) {
		AdminLogic.changeDestitation(trackingNumber, destitationLogin);
	}

	public void printIsSender() {
		Printer.printIsSender();
	}

	public void editInfo(String userName, String street, String city, String zipCode) {
		Logic.editAddress(userName, street, city, zipCode);
	}

	public void getAllPackage() {
		AdminLogic.getAllPackage();
	}

	public void register(String userName, String realName, String street, String city, String zipCode) {
		Logic.register(userName, realName, street, city, zipCode, BACKEND_FACADE);
	}

	public void editPackage(String trackingNumber, String currentLocation, String priorityID, String shippingTime,
			String status, String receiver, String sender) {
		AdminLogic.editPackage(trackingNumber, currentLocation, priorityID, shippingTime, status, receiver, sender);
	}

	public void printEditPackage(int count) {
		Printer.printEditPackage(count);
		;
	}

	public void addWarehouse() {
		ShippingLogic.addWarehouse();
	}

	public void confirmArrived(String trackingNumber) {
		BACKEND_FACADE.setPackageArrived(trackingNumber);
	}

	public ArrayList<String> getWarehouse() {
		return ShippingLogic.warehouse;
	}

	public void newPackage(String login, String destinationLogin) {
		UserLogic.newPackage(login, destinationLogin);
	}

	public void printSendPackage(int count) {
		Printer.printSendPackage(count);
	}

	public void returnPackage(String trackingNumber) {
		BACKEND_FACADE.returnPackage(trackingNumber);
		UserLogic.returnPackage(trackingNumber);
	}

	public void checkPackage(String trackingNumber, String login, boolean isSender, boolean onGoing) {
		UserLogic.checkPackage(trackingNumber, login, isSender, onGoing);
	}

	public void printCheckPackage() {
		Printer.printCheckPackage();
	}

	public void printAskTracking() {
		Printer.printAskTracking();
	}

	public void becomeVIP(String userLogin) {
		UserLogic.becomeVIP(userLogin);
	}

	public void cancelPackage(String trackingNumber) {
		BACKEND_FACADE.deletePakcageRecord(trackingNumber);
		UserLogic.cancelPackage(trackingNumber);
	}

	public void holdAtWarehouse(String trackingNumber) {
		UserLogic.holdAtWarehouse(trackingNumber);
	}

	public void estimatePackage(String trackingNumber) {
		UserLogic.estimatePackage(trackingNumber);
	}

	public GoogleGeocode getLatLng(String street, String city, String zipCode) {
		return GoogleGeocode.getLatLng(street, city, zipCode);
	}

	public double distance(double lat1, double lon1, double lat2, double lon2) {
		return CalculateDistance.distance(lat1, lon1, lat2, lon2, "M");
	}
}
