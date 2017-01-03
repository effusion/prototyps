package ch.andreas.thesis.mongo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heuby on 28.12.16.
 */
@Document(collection = "persons")
public class Person {

    private long documentVersion = 2;

    @Id
    private String id;

    private String surName;

    private String firstName;

    private List<Address> addressList;

    private String streetName;

    private int houseNumber;

    private String town;

    private int zip;

    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Deprecated()
    public String getStreetName() {
        return streetName;
    }

    @Deprecated
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Deprecated
    public int getHouseNumber() {
        return houseNumber;
    }

    @Deprecated
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Deprecated
    public String getTown() {
        return town;
    }

    @Deprecated
    public void setTown(String town) {
        this.town = town;
    }

    @Deprecated
    public int getZip() {
        return zip;
    }

    @Deprecated
    public void setZip(int zip) {
        this.zip = zip;
    }

    @Deprecated
    public String getCountry() {
        return country;
    }

    @Deprecated
    public void setCountry(String country) {
        this.country = country;
    }

    public long getDocumentVersion() {
        return documentVersion;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
