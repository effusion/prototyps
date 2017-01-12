package ch.andreas.thesis.graphql.schema;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;

/**
 * Created by heuby on 12.01.17.
 */
public class Schema {


    private static List<Person> personList = new ArrayList<>();
    static {
        Person person1 = new Person();
        person1.setFirstName("Andreas");
        person1.setLastName("Heubeck");
        person1.setStreetName("Ackerstrasse");
        person1.setHouseNumber(44L);
        person1.setTown("Zürich");

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
        //person2.setStreetName("Riedweg");
        //person2.setHouseNumber(14L);
        //person2.setTown("Dübendorf");

        Address address2 = new Address();
        address2.setStreetName("Riedweg");
        address2.setHouseNumber(14);
        address2.setTown("Dübendorf");
        addressList = new ArrayList<>();
        addressList.add(address2);
        person2.setAddresses(addressList);

        personList.add(person2);
    }

    public static GraphQLSchema getSchema(){

        GraphQLObjectType queryRoot = newObject()
                .name("QueryType")
                .field(newFieldDefinition()
                        .name("persons")
                        .description("Retuns all persons")
                        .type(new GraphQLList(PERSON_TYPE))
                        .dataFetcher(env -> personList)
                        .build()
                )
                .field(newFieldDefinition()
                        .name("person")
                        .description("Return person based on name")
                        .type(PERSON_TYPE)
                        .argument(newArgument()
                                .name("fn")
                                .description("The firstname of the person to be searched.")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                        )
                        .dataFetcher(env -> findPersonByFirstName(env.getArgument("fn")))
                        .build()
                )
        .build();

        return newSchema().query(queryRoot).build();

    }

    private static Person findPersonByFirstName(Object firstName) {
        String param = (String) firstName;
        Optional<Person> personOptional = personList.stream().filter(person -> person.getFirstName().toLowerCase().equals(param.toLowerCase())).findFirst();
        return personOptional.get();
    }

    private static final GraphQLObjectType ADDRESS_TYPE = newObject()
            .name("Address")
            .field(newFieldDefinition()
                    .name("streetName")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .field(newFieldDefinition()
                    .name("houseNumber")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build()
            )
            .field(newFieldDefinition()
                    .name("town")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .build();

    private static final GraphQLObjectType PERSON_TYPE = newObject()
            .name("Person")
            .field(newFieldDefinition()
                    .name("firstName")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .field(newFieldDefinition()
                    .name("lastName")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .field(newFieldDefinition()
                    .name("streetName")
                    .type(GraphQLString)
                    .build()
            )
            .field(newFieldDefinition()
                    .name("houseNumber")
                    .type(GraphQLLong)
                    .build()
            )
            .field(newFieldDefinition()
                    .name("town")
                    .type(GraphQLString)
                    .build()
            )
            .field(newFieldDefinition()
                    .name("Address")
                    .type(ADDRESS_TYPE)
                    .build()
            )
            .build();


}
