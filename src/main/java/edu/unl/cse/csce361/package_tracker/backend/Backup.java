package edu.unl.cse.csce361.package_tracker.backend;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Backup {
    private static final BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();

    public static void initialDataBase () throws NoSuchAlgorithmException {
        SecureRandom random1 = SecureRandom.getInstance("SHA1PRNG");
        Address address1 = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
        Sender sender1 = new Sender(address1, "UNL", "sxc258");
        Address address2 = new Address("122 East Jorgenson Street", "Chicago", "60613");
        Receiver receiver1 = new Receiver(address2, "dddsx258");
        BACKEND_FACADE.addPackageRecord(sender1, receiver1, Math.abs(random1.nextInt() % 8));
        Address address3 = new Address("1705 Arbor Dr, Lincoln", "Lincoln", "68503");
        Sender sender2 = new Sender(address3, "University of Nebraska - Lincoln East Union", "unl-east");
        Address address4 = new Address("187 N 2nd Street", "Lincoln", "68116");
        Receiver receiver2 = new Receiver(address4, "Bryan");
        BACKEND_FACADE.addPackageRecord(sender2, receiver2, Math.abs(random1.nextInt() % 8));
        Address address5 = new Address("6001 Dodge Street", "Omaha", "68182");
        Sender sender3 = new Sender(address5, "University of Nebraska - Omaha", "uno");
        Address address6 = new Address("8896 East 1st Ave.", "Dallas", "75001");
        Receiver receiver3 = new Receiver(address6, "Ben");
        BACKEND_FACADE.addPackageRecord(sender3, receiver3, Math.abs(random1.nextInt() % 8));
        Address address7 = new Address("42nd and Emile", "Omaha", "68198");
        Sender sender4 = new Sender(address7, "University of Nebraska Medical Center", "medical center");
        Address address8 = new Address("4235 Pepper Rd", "Barton", "05822");
        Receiver receiver4 = new Receiver(address8, "James");
        BACKEND_FACADE.addPackageRecord(sender4, receiver4, Math.abs(random1.nextInt() % 8));
        Address address9 = new Address("12916 Millard Airport Plaza", "Omaha", "68137");
        Sender sender5 = new Sender(address9, "Millard Airport", "millard airport");
        Address address10 = new Address("184 Marvel Way", "New York", "10453");
        Receiver receiver5 = new Receiver(address10, "Howard");
        BACKEND_FACADE.addPackageRecord(sender5, receiver5, Math.abs(random1.nextInt() % 8));
        Address address11 = new Address("4501 Abbott Dr", "Omaha", "68110");
        Sender sender6 = new Sender(address11, "Millard Airport East", "millard airport-east");
        Address address12 = new Address("4 Red Jazz Way", "Toronto", "M5V1J1");
        Receiver receiver6 = new Receiver(address12, "Howard");
        BACKEND_FACADE.addPackageRecord(sender6, receiver6, Math.abs(random1.nextInt() % 8));
        Address address13 = new Address("16600 Quarry Oaks Dr Ashland", "Ashland", "68003");
        Sender sender7 = new Sender(address13, "Quarry Oaks Golf Club", "golf for ever");
        Address address14 = new Address("366 Fengyuan Way", "Shanghai", "200092");
        Receiver receiver7 = new Receiver(address14, "Bicker");
        BACKEND_FACADE.addPackageRecord(sender7, receiver7, Math.abs(random1.nextInt() % 8));
    }

    public static void main (String[] args) throws NoSuchAlgorithmException {
        initialDataBase();
    }
}
