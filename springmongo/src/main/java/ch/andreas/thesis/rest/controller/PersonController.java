package ch.andreas.thesis.rest.controller;


import ch.andreas.thesis.mongo.data.Address;
import ch.andreas.thesis.mongo.data.Person;
import ch.andreas.thesis.mongo.repository.PersonRepository;
import ch.andreas.thesis.rest.json.PersonJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            PersonJson personJson = null;
            if(person.getDocumentVersion() == 1) {
                personJson = new PersonJson(person.getFirstName(), person.getSurName(), person.getStreetName(),
                        person.getHouseNumber(), person.getTown(), person.getZip(), person.getCountry());
                personJson.setPersonId(person.getId());
            }else if (person.getDocumentVersion() == 2){
                Optional<Address> addressOptional = person.getAddressList().stream().findFirst();
                if(addressOptional.isPresent()) {
                    Address address = addressOptional.get();
                    personJson = new PersonJson(person.getFirstName(), person.getSurName(), address.getStreetName(),
                            address.getHouseNumber(), address.getTown(), address.getZip(), address.getCountry());
                    personJson.setPersonId(person.getId());
                }else {
                    personJson = new PersonJson(person.getFirstName(), person.getSurName(), "",
                            0, "", 0, "");
                    personJson.setPersonId(person.getId());
                }
            }
            personJsonList.add(personJson);
        });
        return personJsonList;
    }

    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public void createPerson(@RequestBody PersonJson personJson){
        convertJsonToDocument(personJson);
    }

    private void convertJsonToDocument(@RequestBody PersonJson personJson) {
        Address address = new Address();
        address.setStreetName(personJson.getStreetName());
        address.setHouseNumber(personJson.getHouseNumber());
        address.setTown(personJson.getTown());
        address.setZip(personJson.getZip());
        address.setCountry(personJson.getCountry());
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        Person person = new Person();
        person.setFirstName(personJson.getFirstName());
        person.setSurName(personJson.getLastName());
        person.setAddressList(addressList);
        personRepository.save(person);
    }
}
