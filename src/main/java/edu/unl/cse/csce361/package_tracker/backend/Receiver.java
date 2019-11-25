package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;

/**
 * @author davidgao
 */
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne(mappedBy = "Address")
    private Address address;
    @Column(name = "name")
    private String name;
    @Column(name  = "packageID")
    private Package packageid;

    private Sender sender;

    public Receiver () {
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

    public Package getPackageid() {
        return packageid;
    }

    public void setPackageid(Package packageid) {
        this.packageid = packageid;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
