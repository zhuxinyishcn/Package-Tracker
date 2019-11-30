package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sender")
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "senderid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Package_ID")
    private Set<Package> packageSet = new HashSet<>();

    public Sender (Address address, String name, String userName) {
        this.address = address;
        this.name = name;
        this.userName = userName;
    }

    public Sender () {
    }

    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public Set<Package> getPackageSet () {
        return packageSet;
    }

    public void setPackageSet (Set<Package> packageSet) {
        this.packageSet = packageSet;
    }
}
