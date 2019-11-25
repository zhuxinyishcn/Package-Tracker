package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;

/**
 * @author davidgao
 */
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "receiverid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Address address;
    @Column(name = "name", nullable = false)
    private String name;
    @MapsId
    @Column(name = "Package_ID", nullable = false)
    private Package packageid;


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


}
