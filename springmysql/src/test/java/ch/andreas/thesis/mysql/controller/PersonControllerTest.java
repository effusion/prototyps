package ch.andreas.thesis.mysql.controller;

import ch.andreas.thesis.mysql.json.PersonJson;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/**
 * Created by heuby on 28.12.16.
 */
public class PersonControllerTest {


    private RestTemplate restTemplate = new RestTemplate();

    @Test
    //@Ignore("for int testing.")
    public void createPerson() throws RestClientException{
        PersonJson personJson = new PersonJson("Anita", "Heubeck");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("content-type", "application/json");
        HttpEntity request = new HttpEntity(personJson,requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/createPerson", HttpMethod.POST, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
