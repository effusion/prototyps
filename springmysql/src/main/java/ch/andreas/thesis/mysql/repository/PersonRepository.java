package ch.andreas.thesis.mysql.repository;

import ch.andreas.thesis.mysql.data.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by heuby on 26.12.16.
 */
public interface PersonRepository extends CrudRepository<Person, Long>{
    List<Person> findByFirstName(String firstName);
}
