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

    public void editPiorityID (String UUID) {
        Package.setPriority(SESSION, UUID);
    }

    public void changeDestination (String trackingNumber, int currentLocation) {
        //  Package.changeDestination(String trackingNumber, String destitationLogin);
        //TODO: may need to change latter for the next sprint
    }


//        public void editPackageAllInfo (String trackingNumber, String currentLocation,
//                                    String priorityID, String shippingTime,
//                                    String status, String receiver, String sender) {
//        Package.editPackageAllInfo(trackingNumber, currentLocation,
//                priorityID, shippingTime,
//                status, receiver, sender);
//    }
    public void addPackageRecord (Sender sender, Receiver receiver,
                                  int currentLocation, double distance) {
        Package.insertPackage(SESSION, sender, receiver, currentLocation, distance);
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


    public int searchPackage (Session session, String trackingNumber) {
        return Package.searchTrackingNumber(session, trackingNumber);
    }

    public void addUser (String userName, String realName, String street,
                         String city, String zipCode) {
        Sender.insertSender(SESSION, userName, realName, street, city, zipCode);
    }

    public void addReceiver (String realName, String street,
                             String city, String zipCode) {
        Receiver.insertReceiver(SESSION, realName, street, city, zipCode);
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
}
