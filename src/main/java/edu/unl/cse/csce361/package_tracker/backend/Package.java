package edu.unl.cse.csce361.package_tracker.backend;


import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name ="Packages")
public class Package {
    @Id
    @GeneratedValue
    private int id;
    @Id
    @GeneratedValue
    private UUID uuid;
    private String senderID;
    private String receiverID;
    private int currentLocation;
    private String status;

    public Package (String senderID,String receiverID, int currentLocation, String status) {
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

    @Column(name = "UUID")
    public UUID getUuid () {
        return uuid;
    }

    public void setUuid (UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "senderID")
    public String getSenderID () {
        return senderID;
    }

    public void setSenderID (String senderID) {
        this.senderID = senderID;
    }

    @Column(name = "receiverID")
    public String getReceiverID () {
        return receiverID;
    }

    public void setReceiverID (String receiverID) {
        this.receiverID = receiverID;
    }

    @Column(name = "receiverID")
    public int getCurrentLocation () {
        return currentLocation;
    }

    public void setCurrentLocation (int currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Column(name = "status")
    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }
}
