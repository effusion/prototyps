package ch.andreas.thesis.graphql.schema;

import graphql.schema.*;

import java.util.*;

import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;

/**
 * Created by heuby on 12.01.17.
 */
public class Schema {

    private static PersonRepository personRepository = new PersonRepository();


    public GraphQLSchema getSchema(){
        return newSchema()
                .query(buildQueryRoot())
                .mutation(buildMutationRoot())
                .build();
    }

    private GraphQLObjectType buildQueryRoot() {
        return newObject()
                .name("QueryType")
                .field(newFieldDefinition()
                        .name("persons")
                        .description("Retuns all persons")
                        .type(new GraphQLList(PERSON_TYPE))
                        .dataFetcher(env -> personRepository.getPersonList())
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
                        .dataFetcher(env -> personRepository.findPersonByFirstName(env.getArgument("fn")))
                        .build()
                    )
                .build();
    }


    private static final GraphQLObjectType ADDRESS_QUERY_TYPE = newObject()
            .name("Address")
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
                    .name("addresses")
                    .type(new GraphQLList(ADDRESS_QUERY_TYPE))
                    .build()
            )
            .build();

    private GraphQLObjectType buildMutationRoot(){
        return newObject()
                .name("MutationType")
                .field(newFieldDefinition()
                        .name("person")
                        .type(PERSON_TYPE)
                        .argument(newArgument()
                                .name("firstName")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                        )
                        .argument(newArgument()
                                .name("lastName")
                                .type(new GraphQLNonNull(GraphQLString))
                                .build()
                        )
                        .argument(newArgument()
                                .name("newAddresses")
                                .type(new GraphQLList(ADDRESS_INPUT_TYPE))
                                .build()
                        )
                        .dataFetcher(env -> {
                            Map<String, Object> envMap = env.getArguments();
                            return addPerson(envMap);
                        })
                        .build()
                )
                .build();
    }

    private static final GraphQLInputObjectType ADDRESS_INPUT_TYPE = newInputObject()
            .name("newAddress")
            .field(newInputObjectField()
                    .name("streetName")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .field(newInputObjectField()
                    .name("houseNumber")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build()
            )
            .field(newInputObjectField()
                    .name("town")
                    .type(new GraphQLNonNull(GraphQLString))
                    .build()
            )
            .build();

    /*
    * Converter methods to fetch all the data out of the map
    */
    private Object addPerson(Map<String, Object> envMap) {
        Person person = new Person();
        person.setFirstName( (String) envMap.get("firstName"));
        person.setLastName( (String) envMap.get("lastName"));
        convertAddress(person,(ArrayList<Object>) envMap.get("newAddresses"));
        personRepository.createPerson(person);
        return person;
    }

    private void convertAddress(Person person, ArrayList<Object> newAddresses) {
        final List<Address> addressList = new ArrayList<>();
        newAddresses.forEach(entry -> {
            LinkedHashMap<Object,Object> addressAttributes = (LinkedHashMap<Object,Object>) entry;
            createAddress(addressList, addressAttributes);
        });
        person.setAddresses(addressList);

    }

    private void createAddress(List<Address> addressList, LinkedHashMap<Object,Object> addressAttributes) {
        Address address = new Address();
        address.setStreetName((String) addressAttributes.get("streetName"));
        address.setHouseNumber((long) addressAttributes.get("houseNumber"));
        address.setTown((String) addressAttributes.get("town"));
        addressList.add(address);
    }
}
