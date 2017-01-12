package ch.andreas.thesis.graphql.schema;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by heuby on 12.01.17.
 */
public class SchemaTest {

    private GraphQL graphQL = new GraphQL(Schema.getSchema());

    @Test
    public void personQuery(){
        ExecutionResult result = graphQL.execute(
                "{persons {firstName lastName streetName houseNumber town} }");
        basicCheck(result);
        String data = result.getData().toString();
        assertTrue(data.contains("Andreas"));
        System.out.println("\nResult:\n"+data.toString());
    }

    private void basicCheck(ExecutionResult result) {
        assertNotNull(result);
        if(!result.getErrors().isEmpty()){
            System.err.println(result.getErrors().toString());
            fail("Result contains errors");
        }
        assertNotNull(result.getData());
    }

    @Test
    public void personQueryWithArgument(){
        ExecutionResult result = graphQL.execute(
                "{person(fn: \"Andreas\") {firstName lastName streetName houseNumber town} }");
        basicCheck(result);
        String data = result.getData().toString();
        assertTrue(data.contains("Andreas"));
        System.out.println("\nResult:\n"+data.toString());
    }
}
