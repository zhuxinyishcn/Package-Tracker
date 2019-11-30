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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "addressid", unique = true, nullable = false, updatable = false)
    private int addressId;
    @Column(name = "Street", nullable = false, length = 100)
    private String street;
    @Column(name = "City", nullable = false, length = 50)
    private String city;
    @Column(name = "zip", nullable = false, length = 10)
    private String zipCode;

    public Address (String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address () {
    }

    public String getZipCode () {
        return zipCode;
    }

    public void setZipCode (String zipcode) {
        this.zipCode = zipcode;
    }

    public int getAddressId () {
        return addressId;
    }

    public void setAddressId (int addressId) {
        this.addressId = addressId;
    }

    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }


}
