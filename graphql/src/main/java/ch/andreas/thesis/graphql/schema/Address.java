package ch.andreas.thesis.graphql.schema;

/**
 * Created by heuby on 12.01.17.
 */
public class Address {

    private String streetName;

    private long houseNumber;

    private String town;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(long houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
