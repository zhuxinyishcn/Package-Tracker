package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @OneToOne(mappedBy = "receiver", fetch = FetchType.LAZY)
    private Package packageid;
    @Column(name = "destination", nullable = false, length = 100)
    private int destination;

    public Receiver (Address address, String name, int destination) {
        this.address = address;
        this.name = name;
        this.destination = destination;
    }

    public Receiver () {
    }

    public static void editAddress (Session session, String trackingNumber, Address address) {
        final Transaction transaction = session.beginTransaction();
        try {
            Package packageInfo = Package.searchTrackingNumber(session, trackingNumber);
            packageInfo.getReceiver().setAddress(address);
            session.update(packageInfo);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        }

    }

    public Package getPackageid () {
        return packageid;
    }

    public void setPackageid (Package packageid) {
        this.packageid = packageid;
    }

    public int getDestination () {
        return destination;
    }

    public void setDestination (int destination) {
        this.destination = destination;
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
}