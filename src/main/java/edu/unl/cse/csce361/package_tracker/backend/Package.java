package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author davidgao
 */
@Entity
@Table(name = "Packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "packageid", unique = true, nullable = false, updatable = false)
    private int id;
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "trackingNumber", unique = true, length = 35, updatable = false)
    private String trackingNumber;
    @ManyToOne
    @JoinColumn(name = "senderid", nullable = false)
    private Sender sender;
    @OneToOne(mappedBy = "packageid", fetch = FetchType.LAZY)
    private Receiver receiver;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "priorityID", nullable = false)
    private int priorityid;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "shipping Time", nullable = false, length = 50, updatable = false)
    private String shippingTime;

    public Package () {
    }

    public Package (Sender sender, Receiver receiver, int currentLocation, String status) {
        final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
        this.currentLocation = currentLocation;
        this.status = status;
        this.shippingTime = date.format(now);
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
