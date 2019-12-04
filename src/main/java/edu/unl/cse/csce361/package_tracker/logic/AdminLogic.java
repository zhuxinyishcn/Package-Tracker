package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.HibernateUtil;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import org.hibernate.Session;

public class AdminLogic {
	public static void changeDestitation(String trackingNumber, String destitationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destitation
		BackendFacade.getBackendFacade().changeDestination(trackingNumber, destitationLogin);
	}

	public static void getAllPackage() {
		System.out.println(String.format("%-20s %-10s %-10s %-20s %-10s", "Tracking Number", "Sender", "Receiver",
				"Current Location", "Status"));
		// TODO: Get all package info
		int i = 0;
		for (Package p : BackendFacade.getBackendFacade().retrievePackages()
		) {
			System.out.printf("Tracking Number:%-20s\nSender:%-10s\n Receiver:%-10s\n %-20s %-10s\n\n", p.getTrackingNumber(), p.getSender().getName(), p.getReceiver().getName(),
					ShippingLogic.warehouse.get(i), p.getStatus());
			i++;
		}
	}

	public static void editPackage(String trackingNumber, String currentLocation, String priorityID,
								   String shippingTime, String status, String receiver, String sender) {
		if (trackingNumber.length() <= 40) {
			System.err.println("Tracking number should less than 40 charactor.");
		}
		while (trackingNumber.length() <= 40) {
			if (currentLocation != null) {
				if (currentLocation.length() <= 10) {
					// TODO: Using @trackingNumber to set @currentLocation
					BackendFacade.getBackendFacade().changeDestination(trackingNumber, currentLocation);
					System.out.println("You have successfully set Current Location for " + trackingNumber + " to "
							+ currentLocation + ".");
				} else {
					System.err.println("Current Location should be less than 10 charactor.");
				}
				if (priorityID != null) {
					if (priorityID.length() <= 10) {
						// TODO: Using @trackingNumber to set @priorityID
						System.out.println(
								"You have successfully set priorityID for " + trackingNumber + " to " + priorityID + ".");
					} else {
						System.err.println("Priority ID should be less than 10 charactor.");
					}
					if (shippingTime != null) {
						if (shippingTime.length() <= 100) {
							// Using @trackingNumber to set @shippingTime
							System.out.println("You have successfully set shipping time for " + trackingNumber + " to "
									+ shippingTime + ".");
						} else {
							System.err.println("Shipping Time should be less than 99 charactor.");

						}
						if (status != null) {
							if (status.length() <= 50) {
								// Using @trackingNumber to set @status
								System.out
										.println("You have successfully set status for " + trackingNumber + " to " + status + ".");
							} else
								System.err.println("status should be less than 49 charactor.");
						}
						if (receiver != null) {
							if (receiver.length() <= 11) {
								// Using @trackingNumber to set @receiver
								System.out.println(
										"You have successfully set receiver for " + trackingNumber + " to " + receiver + ".");
							} else
								System.err.println("receiver should be less than 49 charactor.");
						}
						if (sender != null) {
							if (sender.length() <= 11) {
								// Using @trackingNumber to set @sender
								System.out
										.println("You have successfully set sender for " + trackingNumber + " to " + sender + ".");
							} else {
								System.err.println("sender should be less than 49 charactor.");
							}
						}
					}
				}

				public static boolean confirmPackage (String trackingNumber){
					// TODO: using @trackingNumber to confirm package
					boolean success = false;
					try {
						BackendFacade.getBackendFacade().setPackageArrived(trackingNumber);
					} catch (RuntimeException e) {
						//wait();
					}
				}
			}
		}
	}


}
