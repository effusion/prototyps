package ch.andreas.thesis.mongo.repository;

import ch.andreas.thesis.mongo.data.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by heuby on 28.12.16.
 */
public interface PersonRepository extends MongoRepository<Person, String>{

   List<Person> findByFirstNameIgnoreCase(String firstName);

}
