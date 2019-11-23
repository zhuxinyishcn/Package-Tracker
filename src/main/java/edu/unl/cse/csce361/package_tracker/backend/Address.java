package edu.unl.cse.csce361.package_tracker.backend;

import javax.persistence.*;


/**
 * @author davidgao
 * Address class for address sql table
 */
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @Column(name = "AddressID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
    @Column(name  = "Street")
    private String street;
    @Column(name  = "City")
    private String city;
    @Column(name = "Type")
    private String addressType;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

}
