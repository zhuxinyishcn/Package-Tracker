package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class AdminLogic {
	private static final logicFacade logic = logicFacade.getInstance();
	private static final BackendFacade backend = BackendFacade.getBackendFacade();

	public static void getAllPackage() {
		// Get all package from data base
		Printer.printLogicAllPackage(backend.retrievePackages());
	}

	public static void editCurrentLocation(String trackingNumber, int currentLocation) {
		// By provided @trackingNumber to locate the package to change the
		// @currentLocation in database.
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");// Max 40 character for @trackingNumber.
		} else {
			if (currentLocation <= 12 && currentLocation > 0) { // @currentLocation should between 1 and 12
				Printer.printLogicLoading();
				backend.editCurrentlocation(trackingNumber, currentLocation);
				Printer.printLogicRequestSuccess("edit current location");
			} else {
				Printer.printErrInput("Current location", "10");
			}
		}
	}

	public static void editPriorityID(String trackingNumber, int priorityID) {
		// By provided @trackingNumber to locate the package to change the @priorityID
		// in database.
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (priorityID <= 99 && priorityID >= 0) { // @ priorityID should less than 2 character.
				Printer.printLogicLoading();
				backend.editPiorityID(trackingNumber);
				Printer.printLogicRequestSuccess("edit priority ID");
			} else {
				Printer.printErrInput("Priority ID", "2");
			}
		}
	}

	public static void editStatus(String trackingNumber, String status) {
		// By provided @trackingNumber to locate the package to change the @status in
		// database.
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (status.length() <= 50) { // Should be less than 50 character.
				Printer.printLogicLoading();
				backend.editPackageStatus(trackingNumber, status);
				Printer.printLogicRequestSuccess("edit status");
			} else {
				Printer.printErrInput("Status", "50");
			}
		}
	}

	public static void editReceiver(String trackingNumber, String street, String city, String zipCode) {

		// By provided @trackingNumber to locate the package to change the receiver and
		// get the new Geocode to direct the package.
		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) { // street less than 100, city
																							// 50, zip 10 character.
				GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
				String lat = geocode.getLat();
				String lng = geocode.getLng();
				Printer.printLogicLoading();
				//TODO:

				Printer.printLogicRequestSuccess("edit receiver");
			} else {
				Printer.printLogicErrAddress();
			}
		}
	}

	public static void confirmPackage(String trackingNumber) { // Set package status to confirm received.
		Printer.printLogicLoading();
		backend.editPackageArrived(trackingNumber);
		Printer.printLogicRequestSuccess("confirm package received");
	}

	public static void cancelPackage(String trackingNumber) { // Set package status to cancel, the package will be
		Printer.printLogicLoading();						// destroyed.
		backend.deletePakcageRecord(trackingNumber);
	}

	public static void holdPackage(String trackingNumber) { // Hold package at warehouse to wait for customer to pickup
		Printer.printLogicLoading();
		backend.editPackageStatus(trackingNumber, "hold");
	}

	public static void estimatePackageTime(String trackingNumber) { // calculate estimate time the user can receive the
																	// package.
		UserLogic.estimatePackage(trackingNumber);
	}

}
