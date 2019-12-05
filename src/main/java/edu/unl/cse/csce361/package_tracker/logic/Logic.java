package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Address;
import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Sender;

public class Logic {

	public static void editInfo(String isSender, String userName, String realName, String warehouseID, String street,
								String city, String zipCode) {
		if (userName.length() >= 40) {
			System.err.println("User name should less than 40 charactor.");
		}
		int userNameLength = userName.length();
		while (userNameLength <= 40) {
			if (isSender.equalsIgnoreCase("Y")) {
				if (realName != null) {
					if (realName.length() <= 100) {
						// TODO: Using @userName who is a sender to change name to @name.
						System.out.println("You have successfully set the name for " + userName + " to " + realName);
					} else
						System.err.println("Name should be less than 100 charactor.");
				}
				if (street != null) {
					if (street.length() <= 100) {
						// TODO: Using @userName who is a sender to change street to @street.
						System.out.println("You have successfully set the street for " + userName + " to " + street);
					} else
						System.err.println("Street should be less than 100 charactor.");
				}
				if (city != null) {
					if (city.length() <= 50) {
						// TODO: Using @userName who is a sender to change city to @city.
						System.out.println("You have successfully set the city for " + userName + " to " + city);
					} else
						System.err.println("City should be less than 50 charactor.");
				}
				if (zipCode != null) {
					if (zipCode.length() <= 10) {
						// TODO: Using @userName who is a sender to change zipCode to @zipCode.
						System.out.println("You have successfully set the zip for " + userName + " to " + zipCode);
					} else
						System.err.println("zip should be less than 10 charactor.");
				}
				if (warehouseID != null) {
					if (isNumber(warehouseID) && warehouseID.length() <= 5) {
						// TODO: Using @userName who is a sender to change warehouseID to @warehouseID.
						System.out.println(
								"You have successfully set the warehouseID for " + userName + " to " + warehouseID);
					}
				}

			} else {
				if (realName != null) {
					if (realName.length() <= 100) {
						// TODO: Using @userName who is a receiver to change name to @name.
						System.out.println("You have successfully set the name for " + userName + " to " + realName);
					} else
						System.err.println("Name should be less than 100 charactor.");
				}
				if (street != null) {
					if (street.length() <= 100) {
						// TODO: Using @userName who is a receiver to change street to @street.
						System.out.println("You have successfully set the street for " + userName + " to " + street);
					} else
						System.err.println("Street should be less than 100 charactor.");
				}
				if (city != null) {
					if (city.length() <= 50) {
						// TODO: Using @userName who is a receiver to change city to @city.
						System.out.println("You have successfully set the city for " + userName + " to " + city);
					} else
						System.err.println("City should be less than 50 charactor.");
				}
				if (zipCode != null) {
					if (zipCode.length() <= 10) {
						// TODO: Using @userName who is a receiver to change zipCode to @zipCode.
						System.out.println("You have successfully set the zip for " + userName + " to " + zipCode);
					} else
						System.err.println("zip should be less than 10 charactor.");
				}
				if (warehouseID != null) {
					if (isNumber(warehouseID) && warehouseID.length() <= 5) {
						// TODO: Using @userName who is a receiver to change warehouseID to
						// @warehouseID.
						System.out.println(
								"You have successfully set the warehouseID for " + userName + " to " + warehouseID);
					}
				}
			}
			userNameLength = 41;
		}
	}


	public static boolean checkVip(String login) {
		// TODO: using @login to check user is vip.
		int vip = 0;

		if (vip == 1) {
			return true;
		} else
			return false;
	}

	public static void register(String isSender, String userName, String realName, String warehouseID, String street,
								String city, String zipCode, BackendFacade backendFacade) {
		// Using @login to search is there a login exist
		boolean legal = true;
		if (userName.length() >= 10 || userName.isEmpty()) {
			System.err.println("Username should be less than 10 charactor.");
			legal = false;
		}
		if (realName.length() >= 100 || realName.isEmpty()) {
			System.err.println("Username should be less than 100 charactor.");
			legal = false;
		}
		if (warehouseID.length() >= 5 && isNumber(warehouseID) || warehouseID.isEmpty()) {
			System.err.println("warehouseID should be less than 5 interger.");
			legal = false;
		}
		if (street.length() >= 100 || street.isEmpty()) {
			System.err.println("Street should be less than 100 charactor.");
			legal = false;
		}
		if (city.length() >= 50 || city.isEmpty()) {
			System.err.println("city should be less than 50 charactor.");
			legal = false;
		}
		if (zipCode.length() >= 10 || zipCode.isEmpty()) {
			System.err.println("city should be less than 10 charactor.");
			legal = false;
		}
		if (legal) {
			if (isSender.equalsIgnoreCase("Y")) {
				backendFacade.registerUser(userName, realName, street, city, zipCode);
				System.out.println("You have successfully signup as sender, your username is " + userName);
			} else {
				backendFacade.registerReceiver(realName, street, city, zipCode);
				System.out.println("You have successfully signup as receiver, your username is " + userName);
			}
		}
	}

	public static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;
		return true;
	}

}
