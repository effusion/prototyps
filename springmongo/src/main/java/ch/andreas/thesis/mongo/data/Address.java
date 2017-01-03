package ch.andreas.thesis.mongo.data;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by heuby on 03.01.17.
 */
@Document(collection = "addresses")
public class Address {

    private long documentVersion = 1;

    private String streetName;

    private int houseNumber;

    private String town;

    private int zip;

    private String country;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getDocumentVersion() {
        return documentVersion;
    }
}
