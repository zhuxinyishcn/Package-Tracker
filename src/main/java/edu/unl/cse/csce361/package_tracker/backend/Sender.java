package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

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

    public static void deleteUser (Session session,  int userId) {
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

    public static void insertSender (Session session, String userName, String realName,
                                     String street,
                                     String city, String zipCode) {
        final Transaction transaction = session.beginTransaction();
        try {
            Address address = new Address(street, city, zipCode);
            Sender sender = new Sender(address, realName, userName);
            session.persist(sender);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw e;
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

    @Override
    public String toString () {
        return "Sender{" +
                "id=" + id +
                ", address=" + address.getCity() + " " + address.getStreet() + " " + address.getZipCode() +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", packageSet=" + packageSet +
                '}';
    }
}