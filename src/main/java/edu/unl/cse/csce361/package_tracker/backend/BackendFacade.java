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

    public static void main (String[] args) {
        final BackendFacade backendFacade = BackendFacade.getBackendFacade();
        Address address = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
        Sender sender = new Sender(address, "test", "sxc258");
        Address address2 = new Address("1400 R St2, Lincoln, NE 68588", "Lincoln", "68508");
        Receiver receiver = new Receiver(address2, "dddsx258");
        backendFacade.addPackageRecord(sender, receiver, 1);

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
}
