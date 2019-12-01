package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @JoinColumn(name = "PackageSet")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Package> packageSet = new HashSet<>();

    public Sender (Address address, String name, String userName) {
        this.address = address;
        this.name = name;
        this.userName = userName;
    }

    public Sender () {
    }

    public static void deleteUser (int userId) {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Sender user = session.get(Sender.class, userId);
            Address address = user.getAddress();
            session.delete(user);
            session.delete(address);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession(session);
        }
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

