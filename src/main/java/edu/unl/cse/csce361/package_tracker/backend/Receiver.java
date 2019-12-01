package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author davidgao
 */
@Entity
@Table(name = "Receiver", uniqueConstraints = {
        @UniqueConstraint(columnNames = "receiverid")})
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "receiverid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "address")
    private Address address;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToOne(mappedBy = "receiver", fetch = FetchType.LAZY)
    private Package packageid;

    public Receiver (Address address, String name) {
        this.address = address;
        this.name = name;
    }

    public Receiver () {
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


    public Package getPackageid () {
        return packageid;
    }

    public void setPackageid (Package packageid) {
        this.packageid = packageid;
    }


}
