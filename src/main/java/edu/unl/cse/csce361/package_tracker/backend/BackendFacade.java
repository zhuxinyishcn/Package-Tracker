package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Observable;

public class BackendFacade extends Observable {
    private static BackendFacade instance;
    private final Session session = HibernateUtil.createSession().openSession();
    private final Transaction transaction = session.beginTransaction();

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

    public void editDestination (String trackingNumber, String destitationLogin) {
        //  Package.changeDestination(String trackingNumber, String destitationLogin);
        //TODO: may need to change latter for the next sprint
    }

    public void addPackageRecord (Sender sender, Receiver receiver,
                                  int currentLocation, double distance) {
        Package.insertPackage(session, transaction, sender, receiver, currentLocation, distance);
    }

    public void editPiorityID () {

    }

    public void deletePakcageRecord (String UUID) {
        Package.deletePakcage(session, transaction, UUID);
    }

    public void deleteUser (int userId) {
        Sender.deleteUser(session, transaction, userId);
    }

    public void editPackageArrived (String UUID) {
        Package.setPackage(session, transaction, UUID);
    }

    public void editPackageStatus (String UUID, String status) {
        Package.setPackage(session, transaction, UUID, status);
    }


    public List<Package> retrievePackages () {
        return Package.retrievePackages(session);
    }

    public int searchPackage (Session session, String trackingNumber) {
        return Package.searchTrackingNumber(session, trackingNumber);
    }

//    public void editPackageAllInfo (String trackingNumber, String currentLocation,
//                                    String priorityID, String shippingTime,
//                                    String status, String receiver, String sender) {
//        Package.editPackageAllInfo(trackingNumber, currentLocation,
//                priorityID, shippingTime,
//                status, receiver, sender);
//    }

    public void addUser (String userName, String realName, String street,
                         String city, String zipCode) {
        Sender.insertSender(session, transaction, userName, realName, street, city, zipCode);
    }

    public void addReceiver (String realName, String street,
                             String city, String zipCode) {
        Receiver.insertReceive(session, transaction, realName, street, city, zipCode);
    }

    public void editPackageReceiver (String trackingNumber) {
        Package.returnPackage(session, transaction, trackingNumber);
    }
}
