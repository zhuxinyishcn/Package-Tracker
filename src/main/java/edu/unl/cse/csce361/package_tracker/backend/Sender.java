package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sender", uniqueConstraints = {
        @UniqueConstraint(columnNames = "senderid")})
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SenderID", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;
    @Column(name = "name", nullable = false, length = 100)
    @Field(name = "name", index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @JoinColumn(name = "PackageSet")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Package> packageSet = new HashSet<>();
    @Column(name = "status", nullable = false, length = 100)
    private String status;

    public Sender (Address address, String name, String userName) {
        this.address = address;
        this.name = name;
        this.userName = userName;
        this.status = "Active";
    }

    public Sender () {
    }

    public static void deleteUser (Session session, int userId) {
        final Transaction transaction = session.beginTransaction();
        try {
            Sender user = session.load(Sender.class, userId);
            user.setStatus("History");
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static void insertSender (Session session, Sender sender) {
        final Transaction transaction = session.beginTransaction();
        try {
            session.persist(sender);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static void insertPackage (Session session, Sender sender, Package packageInfo) {
        final Transaction transaction = session.beginTransaction();
        try {
            Set<Package> packageSet = sender.getPackageSet();
            packageSet.add(packageInfo);
            sender.setPackageSet(packageSet);
            session.update(sender);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static String searchSenderStatus (Session session, String userName) {
        try {
            Query query =
                    session.createSQLQuery("select s.status from Sender s  where s.username = :ids").
                            setParameter("ids", userName);
            return (String) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void setSenderStatus (Session session, String userName) {
        final Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        try {
            CriteriaUpdate<Sender> update = builder.createCriteriaUpdate(Sender.class);
            Root e = update.from(Sender.class);
            update.set("status", "VIP");
            update.where(builder.equal(e.get("userName"), userName));
            session.createQuery(update).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Sender searchSender (Session session, String userName) {
        try {
            Query query =
                    session.createSQLQuery("select s.SenderID from Sender s  where s.username = :ids").
                            setParameter("ids", userName);
            int senderid = (int) query.getSingleResult();
            return session.get(Sender.class, senderid);
        } catch (NoResultException e) {
            return null;
        }

    }

    public static void updateAddress (Session session, String userName, Address address) {
        final Transaction transaction = session.beginTransaction();
        try {
            Sender sender = searchSender(session, userName);
            sender.setAddress(address);
            session.update(sender);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
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