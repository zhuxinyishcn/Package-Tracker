package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;

import java.util.List;
import java.util.Observable;

public class BackendFacade extends Observable {
    private static BackendFacade instance;

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


    public void addPackageRecord (Sender sender, Receiver receiver,
                                  int currentLocation) {
        Package.insertPackage(sender, receiver, currentLocation);
    }

    public void deletePakcageRecord (String UUID) {
        Package.deletePakcage(UUID);
    }

    public void deleteUser (int userId) {
        Sender.deleteUser(userId);
    }

    public void setPackageArrived (String UUID) {
        Package.setPackage(UUID);
    }

    public void setPackageStatus (String UUID, String status) {
        Package.setPackage(UUID, status);
    }

    public List<Package> retrievePackages () {
        return Package.retrievePackages();
    }

    public int seachPackage (Session session, String trackingNumber) {
        return Package.searchTrackingNumber(session, trackingNumber);
    }

    public void editPackageAllInfo (String trackingNumber, String currentLocation,
                                    String priorityID, String shippingTime,
                                    String status, String receiver, String sender) {
        Package.editPackageAllInfo(trackingNumber, currentLocation,
                priorityID, shippingTime,
                status, receiver, sender);
    }
}
