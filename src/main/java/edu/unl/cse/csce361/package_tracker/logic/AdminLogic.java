package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class AdminLogic {
	private static final logicFacade logic = logicFacade.getInstance();

	public static void getAllPackage() {
		// TODO: Get all package info
		Printer.printLogicAllPackage(BackendFacade.getBackendFacade().retrievePackages());
	}

	public static void editCurrentLocation(String trackingNumber, int currentLocation) {
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (currentLocation <= 12 && currentLocation > 0) {
				// TODO: Using @trackingNumber to set @currentLocation
				BackendFacade.getBackendFacade().changeDestination(trackingNumber, currentLocation);
				Printer.printLogicRequestSuccess("edit current location");
			} else {
				Printer.printErrInput("Current location", "10");
			}
		}
	}

	public static void editPriorityID(String trackingNumber, int priorityID) {
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (priorityID <= 10 && priorityID >= 0) {
				// TODO: Using @trackingNumber to set @priorityID
				Printer.printLogicRequestSuccess("edit priority ID");
			} else {
				Printer.printErrInput("Priority ID", "10");

			}
		}
	}

	public static void editStatus(String trackingNumber, String status) {
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (status.length() <= 50) {
				// Using @trackingNumber to set @status
				Printer.printLogicRequestSuccess("edit status");
			} else
				Printer.printErrInput("Status", "50");
		}
	}

	public static void editReceiver(String trackingNumber, String street, String city, String zipCode) {

		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
				// Using @trackingNumber to set @receiver address
				GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
				String lat = geocode.getLat();
				String lng = geocode.getLng();
				Printer.printLogicRequestSuccess("edit receiver");
			} else
				Printer.printLogicErrAddress();
		}
	}

	public static void confirmPackage(String trackingNumber) {
		// TODO: using @trackingNumber to confirm package
		BackendFacade.getBackendFacade().setPackageArrived(trackingNumber);
		Printer.printLogicRequestSuccess("confirm package received");
	}

}
