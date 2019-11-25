package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "Sender", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "snenderid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @Column(name = "address", nullable = false)
    private Address address;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Package_ID")
    private HashSet packageSet = new HashSet<Package>();

    public Sender () {
    }
}
