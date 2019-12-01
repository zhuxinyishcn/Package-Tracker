package edu.unl.cse.csce361.package_tracker.backend;

public class BackendFacade {
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
        backendFacade.addPackageRecord(sender, address, receiver, address2, 1);

    }

    public void addPackageRecord (Sender sender, Address senderAddress, Receiver receiver, Address receiverAddress,
                                  int currentLocation) {
        Package.insertPackage(sender, receiver, currentLocation);
    }
}
