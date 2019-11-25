package edu.unl.cse.csce361.package_tracker.backend;

/**
 * @author davidgao
 */
public class Receiver {

    private String id;
    private Address address;
    private String name;
    private Sender sender;
    private Package packageID;

    public Receiver(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Package getPackageID() {
        return packageID;
    }

    public void setPackageID(Package packageID) {
        this.packageID = packageID;
    }
}
