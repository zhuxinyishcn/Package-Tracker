package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Packages")
public class Package implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "trackingNumber", unique = true)
    private String trackingNumber;
    @Column(name = "senderID")
    private String senderID;
    @Column(name = "receiverID")
    private String receiverID;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status")
    private String status;

    public Package (String senderID, String receiverID, int currentLocation, String status) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.currentLocation = currentLocation;
        this.status = status;
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


    public String getSenderID () {
        return senderID;
    }

    public void setSenderID (String senderID) {
        this.senderID = senderID;
    }


    public String getReceiverID () {
        return receiverID;
    }

    public void setReceiverID (String receiverID) {
        this.receiverID = receiverID;
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
}
