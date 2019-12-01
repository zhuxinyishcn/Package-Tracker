package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * @author davidgao
 */
@Entity
@Table(name = "Packages", uniqueConstraints = {
        @UniqueConstraint(columnNames = "packageid"),
        @UniqueConstraint(columnNames = "trackingNumber")})
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "packageid", unique = true, nullable = false, updatable = false)
    private int id;
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "trackingNumber", unique = true, length = 40, updatable = false)
    private UUID trackingNumber;
    @ManyToOne
    @JoinColumn(name = "sender")
    private Sender sender;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", nullable = false)
    private Receiver receiver;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "priorityID", nullable = false)
    private int priorityid;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "shippingTime", nullable = false, length = 100, updatable = false)
    private String shippingTime;

    public Package () {
    }

    public Package (Sender sender, Receiver receiver, int currentLocation) {
        final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
        this.currentLocation = currentLocation;
        this.status = "Shipping";
        this.shippingTime = date.format(now);
        this.trackingNumber = UUID.randomUUID();
    }

    public static void insertPackage (Sender sender, Receiver receiver,
                                      int currentLocation) {
        Session session = HibernateUtil.createSession().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Package packageInfo = new Package(sender, receiver, currentLocation);
            receiver.setPackageid(packageInfo);
            Set<Package> packages = new HashSet<>();
            packages.add(packageInfo);
            sender.setPackageSet(packages);
            session.persist(sender);
            session.persist(packageInfo);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (Throwable e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }
    }

    public String getShippingTime () {
        return shippingTime;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public UUID getTrackingNumber () {
        return trackingNumber;
    }

    public void setTrackingNumber (UUID trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Sender getSender () {
        return sender;
    }

    public void setSender (Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver () {
        return receiver;
    }

    public void setReceiver (Receiver receiver) {
        this.receiver = receiver;
    }

    public int getCurrentLocation () {
        return currentLocation;
    }

    public void setCurrentLocation (int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public int getPriorityid () {
        return priorityid;
    }

    public void setPriorityid (int priorityid) {
        this.priorityid = priorityid;
    }
}
