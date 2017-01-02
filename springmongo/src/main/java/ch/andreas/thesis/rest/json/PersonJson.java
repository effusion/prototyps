package ch.andreas.thesis.rest.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by heuby on 28.12.16.
 */
public class PersonJson {

    @JsonProperty
    private String personId;

    @JsonProperty(required = true)
    private String firstName;

    @JsonProperty(required = true)
    private String lastName;

    @JsonProperty(required = true)
    private String streetName;

    @JsonProperty(required = true)
    private int houseNumber;

    @JsonProperty(required = true)
    private String town;

    @JsonProperty(required = true)
    private int zip;


    @JsonProperty(required = true)
    private String country;

    @JsonCreator
    /**
     * Json constructor
     * @param firstName
     * @param lastName
     * @param streetName
     * @param houseNumber
     * @param town
     * @param zip
     * @param country
     */
    public PersonJson(@JsonProperty("firstName") String firstName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("streetName") String streetName,
                      @JsonProperty("houseNumber") int houseNumber,
                      @JsonProperty("town") String town,
                      @JsonProperty("zip") int zip,
                      @JsonProperty("country") String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.town = town;
        this.zip = zip;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

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
}
