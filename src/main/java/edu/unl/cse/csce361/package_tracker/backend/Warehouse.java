package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "warehouseID", unique = true, nullable = false, updatable = false)
    private int warehouseid;
    @Column(name = "Name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;

    public Warehouse () {
    }

    public Warehouse (String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static void insertWarehouse (Session session, String name, Address address) {
        final Transaction transaction = session.beginTransaction();
        try {
            Warehouse warehouseInfo = new Warehouse(name, address);
            session.persist(warehouseInfo);
            transaction.commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }
    }

    public static List<Warehouse> retrieveWarehouse (Session session) {
        final Transaction transaction = session.beginTransaction();
        return session.createQuery("from Warehouse").list();
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

}