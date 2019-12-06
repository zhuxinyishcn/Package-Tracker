package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Package;

public class AdminLogic {
	private static final logicFacade logic = logicFacade.getInstance();

	public static void changeDestitation(String trackingNumber, String destitationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destitation
		BackendFacade.getBackendFacade().changeDestination(trackingNumber, destitationLogin);
	}

	public static void changeDestination(String trackingNumber, String destinationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destination
	}

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

	public static void editCurrentLocation(String trackingNumber, String currentLocation) {
		if (trackingNumber.length() <= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (currentLocation.length() <= 10) {
				// TODO: Using @trackingNumber to set @currentLocation
				BackendFacade.getBackendFacade().changeDestination(trackingNumber, currentLocation);
				System.out.println("You have successfully set Current Location for " + trackingNumber + " to "
						+ currentLocation + ".");
			} else {
				System.err.println("Current Location should be less than 10 charactor.");
			}
		}
	}

	public static void editPriorityID(String trackingNumber, String priorityID) {
		if (trackingNumber.length() <= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (priorityID.length() <= 10) {
				// TODO: Using @trackingNumber to set @priorityID
				System.out.println(
						"You have successfully set priorityID for " + trackingNumber + " to " + priorityID + ".");
			} else {
				System.err.println("Priority ID should be less than 10 charactor.");
			}
		}
	}

	public static void editShippingTime(String trackingNumber, String shippingTime) {
		if (trackingNumber.length() <= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (shippingTime.length() <= 100) {
				// Using @trackingNumber to set @shippingTime
				System.out.println(
						"You have successfully set shipping time for " + trackingNumber + " to " + shippingTime + ".");
			} else {
				System.err.println("Shipping Time should be less than 99 charactor.");

			}
		}
	}

	public static void editStatus(String trackingNumber, String status) {
		if (trackingNumber.length() <= 40) {
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
		if (trackingNumber.length() <= 40) {
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

	public static void editSender(String trackingNumber, String sender) {
		if (trackingNumber.length() <= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		} else {
			if (sender.length() <= 11) {
				// Using @trackingNumber to set @sender
				System.out.println("You have successfully set sender for " + trackingNumber + " to " + sender + ".");
			} else {
				System.err.println("sender should be less than 49 charactor.");
			}
		}
	}

	public static boolean confirmPackage(String trackingNumber) {
		// TODO: using @trackingNumber to confirm package
		boolean success = false;
		try {
			BackendFacade.getBackendFacade().setPackageArrived(trackingNumber);
			return true;
		} catch (RuntimeException e) {
			// wait();
			return false;
		}
	}

}
