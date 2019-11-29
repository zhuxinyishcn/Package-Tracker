package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * @author davidgao
 */
@Entity
@Table(name = "Packages", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "trackingNumber")})
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "packageid", unique = true, nullable = false, updatable = false)
    private int id;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "trackingNumber", unique = true, length = 35, updatable = false)
    private String trackingNumber;
    @ManyToOne
    @Column(name = "sender", nullable = false)
    private Sender sender;
    @Column(name = "receiver", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Receiver receiver;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "priorityID", unique = true, nullable = false)
    private int priorityid;


    public Package () {
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
}
