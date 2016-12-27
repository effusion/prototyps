package ch.andreas.thesis.mysql.controller;

import ch.andreas.thesis.mysql.data.Person;
import ch.andreas.thesis.mysql.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by heuby on 27.12.16.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/findPerson", method = RequestMethod.GET)
    public List<Person> findPerson(@RequestParam String firstName) {
        return personRepository.findByFirstName(firstName);
    }
}
