package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class UserLogic {

    private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
    private final static logicFacade logic = logicFacade.getInstance();

    public static String checkUser (String userName) {
        return BACKEND_FACADE.searchUserStatus(userName);
    }

    public static void becomeVIP (String userLogin) {
        Printer.printLogicLoading();
        BACKEND_FACADE.editSenderStatus(userLogin);
        Printer.printLogicRequestSuccess("upgrade to VIP");
    }

    public static void returnPackage (String trackingNumber) {
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            BACKEND_FACADE.editPackageReceiver(trackingNumber);
            Printer.printLogicRequestSuccess("return package");
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }

    public static void checkPackageByTrackingNumber (String trackingNumber) {
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            Package packageInfo = BACKEND_FACADE.searchPackage(trackingNumber);
            Printer.printLogicPackageByTrackingNumber(packageInfo);
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }

    public static void checkPackageByUserName (String login) {
        Printer.printLogicLoading();
        Sender sender = BACKEND_FACADE.searchSender(login);
        Set<Package> packages = sender.getPackageSet();
        Printer.printLogicPackageByUserName(packages);
    }

    public static void newPackage (String userName, String receiver, String street, String city, String zipCode) {
        GoogleGeocode desitationGeocode = GoogleGeocode.getLatLng(street, city, zipCode);
        Printer.printLogicLoading();
        Sender user = BACKEND_FACADE.searchSender(userName);
        int desitationWarehouse = logic.findClosestWarehouse(Double.parseDouble(desitationGeocode.getLat()),
                Double.parseDouble(desitationGeocode.getLng()));
        int senderWarehouse = logic.findClosestWarehouse(user.getAddress().getLatitude(),
                user.getAddress().getLongitude());
        double travelDistance = logic.CalculateDistance(Double.parseDouble(desitationGeocode.getLat()),
                Double.parseDouble(desitationGeocode.getLng()), user.getAddress().getLatitude(),
                user.getAddress().getLongitude());
        Address address = new Address(street, city, zipCode, user.getAddress().getLatitude(),
                user.getAddress().getLongitude());
        Receiver receiverInfo = new Receiver(address, receiver, desitationWarehouse);
        String trackingNumber = BACKEND_FACADE.addPackageRecord(user, receiverInfo, senderWarehouse, travelDistance);
        Printer.printLogicNewPackage(trackingNumber);
    }


    public static void cancelPackage (String trackingNumber) { // Without return services
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            BACKEND_FACADE.deletePakcageRecord(trackingNumber);
            Printer.printLogicCencelPackage(trackingNumber);
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }

    public static void holdAtWarehouse (String trackingNumber) {
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            BACKEND_FACADE.editPackageStatus(trackingNumber, "Hold");
            int warehouseID = BACKEND_FACADE.searchPackage(trackingNumber).getCurrentLocation();
            Printer.printLogicHoldWarehouse(warehouseID);
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }

    public static void estimatePackage (String trackingNumber) {
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            String time = BACKEND_FACADE.searchPackage(trackingNumber).getEstimateTime();
            final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.from(date.parse(time));
            Printer.printLogicEstimateTime(dateTime);
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }

    public static void arriveNotify (String trackingNumber) {
        // TODO: How to know package arrived.
        Printer.printLogicArriveNotify(trackingNumber);
    }

    public static void confirmReceive (String trackingNumber) {
        if (logic.isSender(trackingNumber)) {
            Printer.printLogicLoading();
            BACKEND_FACADE.editPackageArrived(trackingNumber);
            Printer.printLogicRequestSuccess("confirm package" + trackingNumber + "received");
        } else {
            Printer.printLogicNotSender(trackingNumber);
        }
    }
}
