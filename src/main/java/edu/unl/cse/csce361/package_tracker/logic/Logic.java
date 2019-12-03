package edu.unl.cse.csce361.package_tracker.logic;

public class Logic {

	public static void editinfo(String userName, String isSender, String name, String street, String city, String zip,
			String warehouseID) {
		if (userName.length() <= 40) {
			System.err.println("User name should less than 40 charactor.");
		}
		while (userName.length() <= 40) {
			if (isSender.equalsIgnoreCase("Y")) {
				if (name != null) {
					if (name.length() <= 100) {
						// TODO: Using @userName who is a sender to change name to @name.
						System.out.println("You have successfully set the name for " + userName + " to " + name);
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
				if (zip != null) {
					if (zip.length() <= 10) {
						// TODO: Using @userName who is a sender to change zipCode to @zip.
						System.out.println("You have successfully set the zip for " + userName + " to " + zip);
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
				if (name != null) {
					if (name.length() <= 100) {
						// TODO: Using @userName who is a receiver to change name to @name.
						System.out.println("You have successfully set the name for " + userName + " to " + name);
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
				if (zip != null) {
					if (zip.length() <= 10) {
						// TODO: Using @userName who is a receiver to change zipCode to @zip.
						System.out.println("You have successfully set the zip for " + userName + " to " + zip);
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

	public void register(String isSender, String login, String realName, String warehouseID, String street, String city,
			String zipCode) {
		// Using @login to search is there a login exist
		boolean legal = true;
		if (login.length() <= 10) {
			System.err.println("Username should be less than 10 charactor.");
			legal = false;
		}
		if (realName.length() <= 100) {
			System.err.println("Username should be less than 100 charactor.");
			legal = false;
		}
		if (warehouseID.length() <= 5 && isNumber(warehouseID)) {
			System.err.println("warehouseID should be less than 5 interger.");
			legal = false;
		}
		if (street.length() <= 100) {
			System.err.println("Street should be less than 100 charactor.");
			legal = false;
		}
		if (street.length() <= 100) {
			System.err.println("Street should be less than 100 charactor.");
			legal = false;
		}
		if (city.length() <= 50) {
			System.err.println("city should be less than 50 charactor.");
			legal = false;
		}
		if (zipCode.length() <= 10) {
			System.err.println("city should be less than 10 charactor.");
			legal = false;
		}
		if (legal) {
			if (isSender.equalsIgnoreCase("Y")) {
				// TODO: Add sender using @login, @realName, @warehouseID to INT, @street,
				// @city, @zipCode to SENDER
				System.out.println("You have successfully signup as sender, your username is " + login);
			} else {
				// TODO: Add receiver using @login, @realName, @warehouseID to INT, @street,
				// @city, @zipCode to RECEIVER
				System.out.println("You have successfully signup as receiver, your username is " + login);
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
