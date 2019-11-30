package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToOne
    @MapsId
    @Column(name = "Package", nullable = false)
    private Set<Package> packageSet = new HashSet<Package>();


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

    public Set<Package> getPackageset() {
        return packageSet;
    }

    public void setPackageid(Package p) { packageSet.add(p);}


}
