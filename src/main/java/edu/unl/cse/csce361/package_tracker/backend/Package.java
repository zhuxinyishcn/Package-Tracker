package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "PackageID", unique = true, nullable = false, updatable = false)
    private int id;
    @Column(name = "trackingNumber", unique = true, length = 40, updatable = false)
    private String trackingNumber;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int priorityid;
    @Column(name = "shippingTime", nullable = false, length = 100, updatable = false)
    private String shippingTime;
    @Column(name = "estimateTime", nullable = false, length = 100, updatable = false)
    private String estimateTime;


    public Package () {
    }

    public Package (Sender sender, Receiver receiver, int currentLocation, double distance) {
        final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
        this.currentLocation = currentLocation;
        this.status = "Shipping";
        this.shippingTime = date.format(now);
        //note: we assume our latest drones latest speed is 22.352 m/s
        final double droneSpeed = 22.352;
        this.estimateTime = date.format(now.plusSeconds((long) (distance / droneSpeed)));
        this.trackingNumber = UUID.randomUUID().toString();
    }

    public static void insertPackage (Session session, Sender sender, Receiver receiver,
                                      int currentLocation, double distance) {
        final Transaction transaction = session.beginTransaction();
        try {
            Package packageInfo = new Package(sender, receiver, currentLocation, distance);
            receiver.setPackageid(packageInfo);
            Set<Package> packages = new HashSet<>();
            packages.add(packageInfo);
            sender.setPackageSet(packages);
            session.persist(sender);
            session.persist(packageInfo);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static void deletePakcage (Session session, String UUID) {
        final Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        try {
            CriteriaUpdate<Package> update = builder.createCriteriaUpdate(Package.class);
            Root e = update.from(Package.class);
            update.set("status", "Canceled");
            update.where(builder.equal(e.get("trackingNumber"), UUID));
            session.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPackage (Session session, String UUID) {
        final Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        try {
            CriteriaUpdate<Package> update = builder.createCriteriaUpdate(Package.class);
            Root e = update.from(Package.class);
            update.set("status", "Arrived");
            update.where(builder.equal(e.get("trackingNumber"), UUID));
            session.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPackage (Session session, String UUID, String status) {
        final Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        try {
            CriteriaUpdate<Package> update = builder.createCriteriaUpdate(Package.class);
            Root e = update.from(Package.class);
            update.set("status", status);
            update.where(builder.equal(e.get("trackingNumber"), UUID));
            session.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Package> retrievePackages (Session session) {
        List<Package> result = session.createQuery("from Package").list();
        return result;
    }

    public static int searchTrackingNumber (Session session, String trackingNumber) {

        Query query =
                session.createSQLQuery("SELECT p.PackageID FROM Packages p WHERE p.trackingNumber like :ids").
                        setParameter("ids", trackingNumber);
        int packageID = (int) query.getSingleResult();
        return packageID;
    }

    public static void editPackageAllInfo (Session session, Transaction transaction, String trackingNumber,
                                           String currentLocation,
                                           String priorityID, String shippingTime,
                                           String status, String receiver, String sender) {
        try {
            int packageid = searchTrackingNumber(session, trackingNumber);
            Package packageInfo = session.get(Package.class, packageid);
            packageInfo.setCurrentLocation(Integer.parseInt(currentLocation));
            packageInfo.setPriorityid(Integer.parseInt(priorityID));
            packageInfo.setStatus(status);
            packageInfo.setShippingTime(shippingTime);
            packageInfo.getSender().setName(sender);
            packageInfo.getReceiver().setName(receiver);
            session.update(packageInfo);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void returnPackage (Session session,  String trackingNumber) {
        final Transaction transaction = session.beginTransaction();
        try {
            int packageid = searchTrackingNumber(session, trackingNumber);
            Package packageInfo = session.get(Package.class, packageid);
            packageInfo.getReceiver().setAddress(packageInfo.getSender().getAddress());
            packageInfo.setStatus("Returned");
            session.update(packageInfo);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentLocation () {
        return currentLocation;
    }

    public void setCurrentLocation (int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getEstimateTime () {
        return estimateTime;
    }

    public void setEstimateTime (String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getShippingTime () {
        return shippingTime;
    }

    public void setShippingTime (String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getTrackingNumber () {
        return trackingNumber;
    }

    public void setTrackingNumber (String trackingNumber) {
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

    @Override
    public int hashCode () {
        return super.hashCode();
    }

    @Override
    public boolean equals (Object obj) {
        return super.equals(obj);
    }
}
