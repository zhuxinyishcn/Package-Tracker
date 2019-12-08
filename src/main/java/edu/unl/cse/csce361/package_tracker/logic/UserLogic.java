package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.util.ArrayList;

public class UserLogic {
    private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();


    public static String checkUser (String userName) {
        return BACKEND_FACADE.searchUserStatus(userName);
    }

    public static void becomeVIP (String userLogin) {
        Printer.printLogicRequestSuccess("upgrade to VIP");
        BACKEND_FACADE.editSenderStatus(userLogin);
    }

    public static void returnPackage (String trackingNumber) {
        BACKEND_FACADE.editPackageReceiver(trackingNumber);
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
//        GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
////        int desitationWarehouse = logic.findClosestWarehouse(Double.parseDouble(geocode.getLat()),
////                Double.parseDouble(geocode.getLng()));
        // TODO: @login and @desinationLogin to create new package.
        //need
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
