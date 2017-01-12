package ch.andreas.thesis.graphql.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by heuby on 12.01.17.
 */
public class PersonRepository {

    private List<Person> personList = new ArrayList<>();

    public PersonRepository(){
        Person person1 = new Person();
        person1.setFirstName("Andreas");
        person1.setLastName("Heubeck");

        Address addressHome = new Address();
        addressHome.setStreetName("Ackerstrasse");
        addressHome.setHouseNumber(44);
        addressHome.setTown("Zürich");

        Address addressWork = new Address();
        addressWork.setStreetName("Hardturmstrasse");
        addressWork.setHouseNumber(201);
        addressWork.setTown("Zürich");

        List<Address> addressList = new ArrayList<>();
        addressList.add(addressHome);
        addressList.add(addressWork);
        person1.setAddresses(addressList);

        personList.add(person1);

        Person person2 = new Person();
        person2.setFirstName("Anita");
        person2.setLastName("Heubeck");
        person2.setStreetName("Riedweg");
        person2.setHouseNumber(14L);
        person2.setTown("Dübendorf");

        personList.add(person2);
    }

    public  Person findPersonByFirstName(Object firstName) {
        String param = (String) firstName;
        Optional<Person> personOptional = personList.stream().filter(person -> person.getFirstName().toLowerCase().equals(param.toLowerCase())).findFirst();
        return personOptional.get();
    }

    public  List<Person> getPersonList(){
        return personList;
    }

    public void createPerson(Person person){
        personList.add(person);
    }
}
