package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

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
@Indexed
@Table(name = "Packages", uniqueConstraints = {
        @UniqueConstraint(columnNames = "packageid"),
        @UniqueConstraint(columnNames = "trackingNumber")})
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PackageID", unique = true, nullable = false, updatable = false)
    private int id;
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Field(name = "trackingNumber", index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "trackingNumber", unique = true, length = 40, updatable = false)
    private String trackingNumber;
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
    @Field(name = "shippingTime", index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
        this.trackingNumber = UUID.randomUUID().toString();
    }

    public static void insertPackage (Sender sender, Receiver receiver,
                                      int currentLocation) {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Package packageInfo = new Package(sender, receiver, currentLocation);
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
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void deletePakcage (String UUID) {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        int packageid = searchTrackingNumber(session, UUID);
        try {
            final Package packages = session.get(Package.class, packageid);
            session.delete(packages);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void setPackage (String UUID) {
        final Session session = HibernateUtil.createSession().openSession();
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
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void setPackage (String UUID, String status) {
        final Session session = HibernateUtil.createSession().openSession();
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
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static List<Package> retrievePackages () {
        final Session session = HibernateUtil.createSession().openSession();
        List<Package> result = (List<Package>) session.createQuery("from Package").list();
        return result;
    }

    public static int searchTrackingNumber (Session session, String trackingNumber) {
        Query query =
                session.createSQLQuery("SELECT p.PackageID FROM Packages p WHERE p.trackingNumber like :ids").
                        setParameter("ids", trackingNumber);
        int packageID = (int) query.getSingleResult();
        return packageID;
    }

    public static void editPackageAllInfo (String trackingNumber, String currentLocation,
                                            String priorityID, String shippingTime,
                                            String status, String receiver, String sender) {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
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
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public String getShippingTime () {
        return shippingTime;
    }

    public void setShippingTime (String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
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

    @Override
    public int hashCode () {
        return super.hashCode();
    }

    @Override
    public boolean equals (Object obj) {
        return super.equals(obj);
    }
}
