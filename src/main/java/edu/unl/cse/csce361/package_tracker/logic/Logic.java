package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;

public class Logic {
	private static final logicFacade logic = logicFacade.getInstance();

	public static void editAddress(String userName, String street, String city, String zipCode) {
		if (userName.length() >= 40) {
			System.err.println("User name should less than 40 charactor.");
		} else {
			if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
				GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
				String lat = geocode.getLat();
				String lng = geocode.getLng();
				// TODO: Using @userName who is a sender to change street to @address.
				System.out.println("You have successfully update your info.");
			} else {
				System.err.println(
						"Street should less than 100 charactor, city should less than 50 charactor, zip code should less than 10 charactor");
			}
		}
	}

	public static void register(String userName, String realName, String street, String city, String zipCode,
			BackendFacade backendFacade) {
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
			GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
			String lat = geocode.getLat();
			String lng = geocode.getLng();
			backendFacade.registerUser(userName, realName, street, city, zipCode);
			System.out.println("You have successfully signup as sender, your username is " + userName);
		}

	}

	public static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;
		return true;
	}

}
