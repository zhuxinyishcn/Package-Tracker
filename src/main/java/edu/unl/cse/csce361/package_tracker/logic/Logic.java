package edu.unl.cse.csce361.package_tracker.logic;

public class Logic {

	public static void editinfo() {
		//TODO:
	}

	public static boolean checkVip(String login) {
		// TODO: using @login to check user is vip.
		int vip = 0;

		if (vip == 1) {
			return true;
		} else
			return false;
	}

	public void register(String login, String realName, String warehouseID, String street, String city,
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
			// TODO: Update database @login, @name, @address, @warehouseIDtoint
			System.out.println(login + realName + warehouseID + street + city + zipCode);
		}
	}

	public static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;

		return true;
	}
}
