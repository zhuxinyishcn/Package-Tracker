package edu.unl.cse.csce361.package_tracker.backend;


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

    public void deletePakcageRecord (int packageid) {
        Package.deletePakcage(packageid);
    }

    public void deleteUser (int userId) {
        Sender.deleteUser(userId);
    }

    public void setPackageArrived (int packageid) {
        Package.setPackage(packageid);
    }

    public void setPackageStatus (int packageid, String status) {
        Package.setPackage(packageid, status);
    }
}
