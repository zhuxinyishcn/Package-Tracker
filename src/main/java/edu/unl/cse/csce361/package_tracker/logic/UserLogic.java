package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.util.ArrayList;

public class UserLogic {

    private static logicFacade logic = logicFacade.getInstance();

    public static String checkUser (String userName) {
        String userType = logic.checkUserStatus(userName);
        return userType;
    }

    public static void becomeVIP (String userLogin) {
        logic.upgradeVIP(userLogin);
        Printer.printLogicRequestSuccess("upgrade to VIP");
    }

    public static void returnPackage (String trackingNumber) {
        // TODO: Set desitation to sender address, Set Geocode.
        Printer.printLogicRequestSuccess("return package");
    }

    public static void checkPackageByTrackingNumber (String trackingNumber) {

        String info = null;// TODO: Using @trackingNumber find package info
        Printer.printLogicPackageByTrackingNumber(info);
    }

    public static void checkPackageByUserName (String login) {
        // TODO: using @login who is a receiver to find package info which is in
        // transit
        ArrayList<String> result = new ArrayList<String>();
        Printer.printLogicPackageByUserName(result);
    }

    public static void newPackage (String userName, String street, String city, String zipCode) {
        GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
        int desitationWarehouse = logic.findClosestWarehouse(Double.parseDouble(geocode.getLat()),
                Double.parseDouble(geocode.getLng()));
        // TODO: @login and @desinationLogin to create new package.
        String trackingNumber = null;
        Printer.printLogicNewPackage(trackingNumber);
    }

    public static void cancelPackage (String trackingNumber) { // Without return services
        // TODO: Remove package
        Printer.printLogicCencelPackage(trackingNumber);
    }

    public static void holdAtWarehouse (String trackingNumber) {
        // TODO: Set @trackingNumber to hold.
        // TODO: Get current location to @warehouseID.
        int warehouseID = 1;
        Printer.printLogicHoldWarehouse(warehouseID);
    }

    public static void estimatePackage (String trackingNumber) {
        int eachLocationTime = 30; // Each stop 30 minutes
        int current = 0;
        int destination = 0;
        // TODO: Using @trackingNumber to get @current @destination
        int estimateMinutes = Math.abs(current - destination) * eachLocationTime + 30;// From last warehouse to final
        // location.
        Printer.printLogicEstimateTime(estimateMinutes);
    }

    public static void arriveNotify (String trackingNumber) {
        // TODO: How to know package arrived.
        Printer.printLogicArriveNotify(trackingNumber);
    }

    public static void confirmReceive (String trackingNumber) {
        // TODO: Set status as received.
        // logic.returnPackage(trackingNumber);
        Printer.printLogicRequestSuccess("confirm package" + trackingNumber + "received");
    }
}
