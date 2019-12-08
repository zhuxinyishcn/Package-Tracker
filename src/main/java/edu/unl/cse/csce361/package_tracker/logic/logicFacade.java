package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Warehouse;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.util.List;

public class logicFacade {
    private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
    private static logicFacade unique;

    private logicFacade () {
    }

    public static logicFacade getInstance () {
        if (unique == null) {
            unique = new logicFacade();
        }
        return unique;
    }

    public void printMainMenu () {
        Printer.printMainMenu();
    }

    public void printAdminMenu () {
        Printer.printAdminMenu();
    }

    public void printUserMenu () {
        Printer.printUserMenu();
    }

    public void printAskUserName () {
        Printer.printAskUserName();
    }

    public void printInvalid () {
        Printer.printInvalid();
    }

    public void printExit () {
        Printer.printExit();
    }

    public void printVIPMenu () {
        Printer.printVIPMenu();
    }

    public void printIsSender () {
        Printer.printIsSender();
    }

    public void editInfo (String userName, String street, String city, String zipCode) {
        Logic.editAddress(userName, street, city, zipCode);
    }

    public void getAllPackage () {
        AdminLogic.getAllPackage();
    }

    public void register (String userName, String realName, String street, String city, String zipCode) {
        Logic.register(userName, realName, street, city, zipCode, BACKEND_FACADE);
    }

    public String checkUser (String userName) {
        return UserLogic.checkUser(userName);
    }

    public void editCurrentLocation (String trackingNumber, int currentLocation) {
        AdminLogic.editCurrentLocation(trackingNumber, currentLocation);
    }

    public void editPriorityID (String trackingNumber, int priorityID) {
        AdminLogic.editPriorityID(trackingNumber, priorityID);
    }

    public void editStatus (String trackingNumber, String status) {
        AdminLogic.editStatus(trackingNumber, status);
    }

    public void editReceiver (String trackingNumber, String street, String city, String zipCode) {
        AdminLogic.editReceiver(trackingNumber, street, city, zipCode);
    }

    public void confirmArrived (String trackingNumber) {
        BACKEND_FACADE.editPackageArrived(trackingNumber);
    }

    public List<Warehouse> getWarehouse () {
        return BACKEND_FACADE.retrieveWarehouse();
    }

    public void newPackage (String login, String street, String city, String zipCode) {
        UserLogic.newPackage(login, street, city, zipCode);
    }

    public void returnPackage (String trackingNumber) {
        BACKEND_FACADE.editPackageReceiver(trackingNumber);
        UserLogic.returnPackage(trackingNumber);
    }

    public void printCheckPackage () {
        Printer.printCheckPackage();
    }

    public void printAskTracking () {
        Printer.printAskTracking();
    }

    public void becomeVIP (String userLogin) {
        UserLogic.becomeVIP(userLogin);
    }

    public void cancelPackage (String trackingNumber) {
        BACKEND_FACADE.deletePakcageRecord(trackingNumber);
        UserLogic.cancelPackage(trackingNumber);
    }

    public void holdAtWarehouse (String trackingNumber) {
        UserLogic.holdAtWarehouse(trackingNumber);
    }

    public void estimatePackage (String trackingNumber) {
        UserLogic.estimatePackage(trackingNumber);
    }

    public GoogleGeocode getLatLng (String street, String city, String zipCode) {
        return GoogleGeocode.getLatLng(street, city, zipCode);
    }

    public double CalculateDistance (double lat1, double lon1, double lat2, double lon2) {
        return CalculateDistance.distance(lat1, lon1, lat2, lon2, "M");
    }

    public int findClosestWarehouse (double lat, double lng) {
        return CalculateDistance.findClosestWarehouse(lat, lng);
    }

    public void checkPackageByTrackingNumber (String trackingNumber) {
        UserLogic.checkPackageByTrackingNumber(trackingNumber);
    }

    public void checkPackageByUserName (String login) {
        UserLogic.checkPackageByUserName(login);
    }

    public String checkUserStatus (String userName) {
        return BACKEND_FACADE.searchUserStatus(userName);
    }

    public void upgradeVIP (String userName) {
        BACKEND_FACADE.editSenderStatus(userName);
    }
}
