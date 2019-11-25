package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sender", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "senderid", unique = true, nullable = false, updatable = false)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Address address;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Package_ID")
    private Set packageSet = new HashSet<Package>();

    public Sender () {
    }
}
