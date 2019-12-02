package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Address;

public class RegisterLogic {

	public void register(String login, String warehouseID, String street, String city, String zipCode) {
		// Using @login to search is there a login exist
		if (login.length()<=10) {
			System.err.println("Username should be less than 10 charactor.");
		}
//		if(login exist) {
//			
//		}
		// TODO: Update database @login, @name, @address, @warehouseIDtoint
	}

	public void deactivate(String login,String confirmation) {
		
		if (confirmation.equalsIgnoreCase("Y")) {
			boolean success = false;
				// TODO: Remove from database @login
				// success = true;
			if (success) {
				System.out.println("User: " + login + " had been removed");
			} else {
				System.out.println("Request Faild, User not found?");
			}

		} else {
			System.out.println("Request canceled.");
		}

	}
}
