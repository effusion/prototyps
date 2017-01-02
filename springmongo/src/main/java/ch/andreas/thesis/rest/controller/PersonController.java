package ch.andreas.thesis.rest.controller;


import ch.andreas.thesis.mongo.data.Person;
import ch.andreas.thesis.mongo.repository.PersonRepository;
import ch.andreas.thesis.rest.json.PersonJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heuby on 27.12.16.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/findPerson", method = RequestMethod.GET)
    public List<PersonJson> findPerson(@RequestParam String firstName) {
        return convert(personRepository.findByFirstNameIgnoreCase(firstName));
    }

    private List<PersonJson> convert(List<Person> byFirstName) {
        final List<PersonJson> personJsonList = new ArrayList<>();
        byFirstName.forEach(person -> {
            PersonJson personJson = new PersonJson(person.getFirstName(), person.getSurName(), person.getStreetName(),
                    person.getHouseNumber(), person.getTown(), person.getZip(), person.getCountry());
            personJson.setPersonId(person.getId());
            personJsonList.add(personJson);
        });
        return personJsonList;
    }

    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public void createPerson(@RequestBody PersonJson personJson){
        Person person = new Person();
        person.setFirstName(personJson.getFirstName());
        person.setSurName(personJson.getLastName());
        person.setStreetName(personJson.getStreetName());
        person.setHouseNumber(personJson.getHouseNumber());
        person.setTown(personJson.getTown());
        person.setZip(personJson.getZip());
        person.setCountry(personJson.getCountry());
        personRepository.save(person);
    }
}
