package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sender", uniqueConstraints = {
        @UniqueConstraint(columnNames = "senderid")})
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "senderid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @JoinColumn(name = "PackageSet")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set packageSet = new HashSet<Package>();

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
