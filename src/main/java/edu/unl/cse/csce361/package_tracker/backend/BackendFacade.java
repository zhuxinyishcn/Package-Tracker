package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;

import java.util.List;
import java.util.Observable;

public class BackendFacade extends Observable {
    private static final Session SESSION = HibernateUtil.createSession().openSession();
    private static BackendFacade instance;
    private static List<Warehouse> warehouses = Warehouse.retrieveWarehouse(SESSION);

    private BackendFacade () {
    }

    /**
     * Returns the current instance of the backEnd facade
     *
     * @return instance --> instance of BackendFacade class
     */
    public static BackendFacade getBackendFacade () {
        if (instance == null) {
            instance = new BackendFacade();
        }
        return instance;
    }

    public void closeConnection () {
        SESSION.close();
    }

    public void editPiorityID (String trackingNumber) {
        Package.setPriority(SESSION, trackingNumber);
    }

    public void editCurrentlocation (String trackingNumber, int currentLocation) {
        Package.setCurrentLocation(SESSION, trackingNumber, currentLocation);
    }

    public String addPackageRecord (Sender sender, Receiver receiver,
                                  int currentLocation, double distance) {
        return Package.insertPackage(SESSION, sender, receiver, currentLocation, distance);
    }

    public void addPackageRecordToSender (Sender sender, Package packageInfo) {
        Sender.insertPackage(SESSION, sender, packageInfo);
    }

    public void deletePakcageRecord (String UUID) {
        Package.deletePakcage(SESSION, UUID);
    }

    public void deleteUser (int userId) {
        Sender.deleteUser(SESSION, userId);
    }

    public void editPackageArrived (String UUID) {
        Package.setPackage(SESSION, UUID);
    }

    public void editPackageStatus (String UUID, String status) {
        Package.setPackage(SESSION, UUID, status);
    }

    public List<Package> retrievePackages () {
        return Package.retrievePackages(SESSION);
    }


    public Package searchPackage (String trackingNumber) {
        return Package.searchTrackingNumber(SESSION, trackingNumber);
    }

    public void addUser (Sender sender) {
        Sender.insertSender(SESSION, sender);
    }


    public void editPackageReceiver (String trackingNumber) {
        Package.returnPackage(SESSION, trackingNumber);
    }

    public void addWareHouse (String name, Address address) {
        Warehouse.insertWarehouse(SESSION, name, address);
    }

    public List<Warehouse> retrieveWarehouse () {
        return warehouses;
    }

    public String searchUserStatus (String userName) {
        return Sender.searchSenderStatus(SESSION, userName);
    }

    public void editSenderStatus (String userName) {
        Sender.setSenderStatus(SESSION, userName);
    }

    public Sender searchSender (String userName) {
        return Sender.searchSender(SESSION, userName);
    }

    public void editSenderAddress (String userName, Address address) {
        Sender.updateAddress(SESSION, userName, address);
    }

    public List<Package> getDispatchingPackage() {
       return Package.getDispatchingPackage(SESSION);
    }
}
