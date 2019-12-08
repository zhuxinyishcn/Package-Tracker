package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Address;
import edu.unl.cse.csce361.package_tracker.backend.Sender;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class Logic {
//    private static final logicFacade logic = logicFacade.getInstance();

    public static void editAddress (String userName, String street, String city, String zipCode) {
        if (userName.length() >= 10) {
            Printer.printErrInput("User Name", "10");
        } else {
            if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
                GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
                String lat = geocode.getLat();
                String lng = geocode.getLng();
                Address address = new Address(street, city, zipCode, Double.parseDouble(lat),
                        Double.parseDouble(lng));
                // TODO: Using @userName who is a sender to change street to @address.
                Printer.printLogicRequestSuccess("edit address");
            } else {
                Printer.printLogicErrAddress();
            }
        }
    }

    public static Sender register (String userName, String realName, String street,
                                   String city, String zipCode) {
        // Using @login to search is there a login exist
        boolean legal = true;
        Double lat = null;
        Double lng = null;
        if (userName.length() >= 10 || userName.isEmpty()) {
            Printer.printErrInput("User Name", "10");
            legal = false;
        }
        if (realName.length() >= 100 || realName.isEmpty()) {
            Printer.printErrInput("Name", "100");
            legal = false;
        }
        if (street.length() >= 100 || street.isEmpty()) {
            Printer.printLogicErrAddress();
            legal = false;
        }
        if (city.length() >= 50 || city.isEmpty()) {
            Printer.printLogicErrAddress();
            legal = false;
        }
        if (zipCode.length() >= 10 || zipCode.isEmpty()) {
            Printer.printLogicErrAddress();
            legal = false;
        }
        if (legal) {
            GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
            lat = Double.parseDouble(geocode.getLat());
            lng = Double.parseDouble(geocode.getLng());
            Printer.printLogicRequestSuccess("sign up an account, your user name is " + userName);
        }
        Address address = new Address(street,
                city, zipCode, lat, lng);
        return new Sender(address, realName, userName);
    }

    public static boolean isNumber (String s) {
        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i)))
                return false;
        return true;
    }


}
