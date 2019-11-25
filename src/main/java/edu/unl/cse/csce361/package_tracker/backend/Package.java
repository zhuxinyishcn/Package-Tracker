package edu.unl.cse.csce361.package_tracker.backend;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Packages", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "trackingNumber"),
        @UniqueConstraint(columnNames = "priorityID")})
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private int id;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "trackingNumber", unique = true, length = 35, updatable = false)
    private String trackingNumber;
    @Column(name = "senderID")
    private int senderid;
    @Column(name = "receiverID")
    private int receiverid;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "priorityID", unique = true, nullable = false)
    private int priorityid;

    public Package () {}


}
