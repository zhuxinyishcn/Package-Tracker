package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.bridge.builtin.DefaultStringBridge;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "Warehouse")
public class Warehouse extends DefaultStringBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @DocumentId
    @Column(name = "warehouseID", unique = true, nullable = false, updatable = false)
    private int warehouseID;
    @Column(name = "Name")
    @Field(name = "Name", index = Index.YES)
    private String name;
    @Field(name = "Address", index = Index.YES)
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "Address", nullable = false)
    private String address;
    @Column(name = "Longitude")
    @Field
    private String longitude;
    @Column(name = "Latitude")
    @Field
    private String Latitude;

    public Warehouse () {
    }

    public Warehouse (String name, String address, String Longitude, String Latitude) {
        this.name = name;
        this.address = address;
        this.longitude = Longitude;
        this.Latitude = Latitude;
    }

    public static void insertWarehouse (String name, String address, String southLatitude, String northLatitude) {
        Session session = HibernateUtil.createSession().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Warehouse warehouseInfo = new Warehouse(name, address, southLatitude, northLatitude);
            session.persist(address);
            session.persist(warehouseInfo);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (Throwable e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }


    }

    public int getWarehouseID () {
        return warehouseID;
    }

    public void setWarehouseID (int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress () {
        return address;
    }


    public void setAddress (String address) {
        this.address = address;
    }

    public String getLongitude () {
        return longitude;
    }

    public void setLongitude (String longitude) {
        longitude = longitude;
    }

    public String getLatitude () {
        return Latitude;
    }

    public void setLatitude (String latitude) {
        Latitude = latitude;
    }
}