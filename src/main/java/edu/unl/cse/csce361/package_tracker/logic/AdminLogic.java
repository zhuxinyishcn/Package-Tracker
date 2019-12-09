package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class AdminLogic {
    private static final logicFacade logic = logicFacade.getInstance();
    private static final BackendFacade backend = BackendFacade.getBackendFacade();

    public static void getAllPackage () {
        Printer.printLogicAllPackage(backend.retrievePackages());
    }

    public static void editCurrentLocation (String trackingNumber, int currentLocation) {
        if (trackingNumber.length() >= 40) {
            Printer.printErrInput("Tracking number", "40");
        } else {
            if (currentLocation <= 12 && currentLocation > 0) {
                backend.editCurrentlocation(trackingNumber, currentLocation);
                Printer.printLogicRequestSuccess("edit current location");
            } else {
                Printer.printErrInput("Current location", "10");
            }
        }
    }

    public static void editPriorityID (String trackingNumber, int priorityID) {
        if (trackingNumber.length() >= 40) {
            Printer.printErrInput("Tracking number", "40");
        } else {
            if (priorityID <= 10 && priorityID >= 0) {
                backend.editPiorityID(trackingNumber);
                Printer.printLogicRequestSuccess("edit priority ID");
            } else {
                Printer.printErrInput("Priority ID", "10");
            }
        }
    }

    public static void editStatus (String trackingNumber, String status) {
        if (trackingNumber.length() >= 40) {
            Printer.printErrInput("Tracking number", "40");
        } else {
            if (status.length() <= 50) {
                backend.editPackageStatus(trackingNumber, status);
                Printer.printLogicRequestSuccess("edit status");
            } else {
                Printer.printErrInput("Status", "50");
            }
        }
    }

    public static void editReceiver (String trackingNumber, String street, String city, String zipCode) {

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

    public static void confirmPackage (String trackingNumber) {
        backend.editPackageArrived(trackingNumber);
        Printer.printLogicRequestSuccess("confirm package received");
    }

    public static void cancelPackage (String trackingNumber) {
        backend.deletePakcageRecord(trackingNumber);
    }

    public static void holdPackage (String trackingNumber) {
        backend.editPackageStatus(trackingNumber, "hold");
    }

    public static void estimatePackageTime (String trackingNumber) {
        UserLogic.estimatePackage(trackingNumber);
    }


}
