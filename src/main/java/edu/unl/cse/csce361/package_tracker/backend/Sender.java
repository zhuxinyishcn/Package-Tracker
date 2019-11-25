package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "Sender", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private int id;
    private Address address;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "name", nullable = false, length = 100)
    private String userName;
    private HashSet packageSet = new HashSet<Package>();

    public Sender () {
    }
}
