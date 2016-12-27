package ch.andreas.thesis.mysql.data;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by heuby on 26.12.16.
 */
@Entity
@Table(name = "T_PERSON")
public class Person extends AbstractPersistable<Long>{

    @Version
    private long version;

    private String lastName;

    private String firstName;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
