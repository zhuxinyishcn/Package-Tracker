package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Address;
import edu.unl.cse.csce361.package_tracker.backend.DatabaseQuerier;

public class RegisterLogic {
	DatabaseQuerier dq = new DatabaseQuerier();

	public void register(String login, String warehouseID, String street, String city, String zipCode) {
		Address address = new Address(street, city, zipCode);
		// TODO: Update database @login, @name, @address, @warehouseIDtoint

//		System.out.println("Please input your username:");
//		login = scan.userIn();
//		System.out.println("Please input your name:");
//		name = scan.userIn();
//		System.out.println("Please input your street name:");
//		String street = scan.userIn();
//		System.out.println("Please input your city:");
//		String city = scan.userIn();
//		System.out.println("Please input your zip code:");
//		String zipCode = scan.userIn();
//		System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
//		ResultSet rs = dq.executeQuery(
//				"SELECT `WarehouseID`,`Name`,`Address` FROM `Warehouse` where `WarehouseID` BETWEEN 1 and 8;", null);
//
//		while (rs.next()) {
//			System.out.println(String.format("%-5s %-30s %-50s", rs.getString(1), rs.getString(2), rs.getString(3)));
//		}
//		rs.close();
//		dq.closeConn();
//		System.out.println("Please select one of the closest warehouse:");
//		System.out.println("User create successful. Your username is: " + login);
	}

	public void deactivate(String login,String confirmation) {
//		System.out.println("Please input your user name:");
//		login = scan.userIn();
//		System.out.println("Confirm remove all data for user: " + login + "(Y/N)");
//		String confirm = scan.userIn();
		
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
