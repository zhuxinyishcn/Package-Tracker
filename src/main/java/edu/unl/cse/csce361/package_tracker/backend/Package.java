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
    @Column(name = "sender",nullable = false)
    private Sender sender;
    @Column(name = "receiver",nullable = false)
    private Receiver receiver;
    @Column(name = "currentLocation")
    private int currentLocation;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "priorityID", unique = true, nullable = false)
    private int priorityid;


    public Package() {}


}
