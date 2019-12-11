package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class VIPLogic {
	private static final logicFacade logic = logicFacade.getInstance();
    public static void changeDestitation (String trackingNumber, String street, String city, String zipCode) {

        if (trackingNumber.length() >= 40) {
            Printer.printErrInput("Tracking number", "40");
        } else {
            if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
                // Using @trackingNumber to set @receiver address
                GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
                String lat = geocode.getLat();
                String lng = geocode.getLng();
                Printer.printLogicRequestSuccess("edit receiver");
            } else {
                Printer.printLogicErrAddress();
            }
        }
    }
}
