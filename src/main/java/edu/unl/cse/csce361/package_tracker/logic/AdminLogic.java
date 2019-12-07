package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Package;

public class AdminLogic {
	private static final logicFacade logic = logicFacade.getInstance();

	public static void getAllPackage() {
		System.out.println(String.format("%-20s %-10s %-10s %-20s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		// TODO: Get all package info
		int i = 0;
		for (Package p : BackendFacade.getBackendFacade().retrievePackages()) {
			System.out.printf("Tracking Number:%-20s\nSender:%-10s\n Receiver:%-10s\n %-20s %-10s\n\n",
					p.getTrackingNumber(), p.getSender().getName(), p.getReceiver().getName(),
					ShippingLogic.warehouse.get(i), p.getStatus());
			i++;
		}
	}

	public static void editCurrentLocation(String trackingNumber, int currentLocation) {
		if (trackingNumber.length() >= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (currentLocation <= 12 && currentLocation > 0) {
				// TODO: Using @trackingNumber to set @currentLocation
				BackendFacade.getBackendFacade().changeDestination(trackingNumber, currentLocation);
				System.out.println("You have successfully set Current Location for " + trackingNumber + " to "
						+ currentLocation + ".");
			} else {
				System.err.println("Warehouse not found.");
			}
		}
	}

	public static void editPriorityID(String trackingNumber, int priorityID) {
		if (trackingNumber.length() >= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (priorityID <= 10 && priorityID >= 0) {
				// TODO: Using @trackingNumber to set @priorityID
				System.out.println(
						"You have successfully set priorityID for " + trackingNumber + " to " + priorityID + ".");
			} else {
				System.err.println("Priority ID should be less than 10 charactor.");
			}
		}
	}

	public static void editStatus(String trackingNumber, String status) {
		if (trackingNumber.length() >= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (status.length() <= 50) {
				// Using @trackingNumber to set @status
				System.out.println("You have successfully set status for " + trackingNumber + " to " + status + ".");
			} else
				System.err.println("status should be less than 49 charactor.");
		}
	}

	public static void editReceiver(String trackingNumber, String street, String city, String zipCode) {
		if (trackingNumber.length() >= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
				// Using @trackingNumber to set @receiver address
				GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
				String lat = geocode.getLat();
				String lng = geocode.getLng();
				System.out.println("You have successfully edit the receiver.");
			} else
				System.err.println(
						"Street should less than 100 charactor, city should less than 50 charactor, zip code should less than 10 charactor");
		}
	}

	public static boolean confirmPackage(String trackingNumber) {
		// TODO: using @trackingNumber to confirm package
		boolean success = false;
		try {
			BackendFacade.getBackendFacade().setPackageArrived(trackingNumber);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

}
