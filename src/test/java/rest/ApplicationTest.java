package rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dela on 29.07.2016.
 * Acceptance Test copied from example.
 */
public class ApplicationTest {

    @Before
    public void startServer() {
        Application.main(new String[0]);
    }

    @Test
    public void exampleScenario() throws UnirestException {

        // Given
        HttpResponse<JsonNode> response1 = Unirest
                .put("http://localhost:8080/transactionservice/transaction/10")
                .body("{ \"amount\": 5000, \"type\":\n \"cars\" }")
                .asJson();

        // Then
        assertEquals("ok", response1.getBody().getObject().get("status"));

        // Given
        HttpResponse<JsonNode> response2 = Unirest
                .put("http://localhost:8080/transactionservice/transaction/11")
                .body("{ \"amount\": 10000, \"type\": \"shopping\", \"parent_id\": 10 }")
                .asJson();

        // Then
        assertEquals("ok", response2.getBody().getObject().get("status"));

        // Given
        HttpResponse<JsonNode> response3 = Unirest
                .get("http://localhost:8080/transactionservice/types/cars")
                .asJson();

        // Then
        assertEquals(1, response3.getBody().getArray().length());
        assertEquals(10, response3.getBody().getArray().getInt(0));

        // Given
        HttpResponse<JsonNode> response4 = Unirest
                .get("http://localhost:8080/transactionservice/sum/10")
                .asJson();

        // Then
        assertEquals(15000, response4.getBody().getObject().get("sum"));

        // Given
        HttpResponse<JsonNode> response5 = Unirest
                .get("http://localhost:8080/transactionservice/sum/11")
                .asJson();

        // Then
        assertEquals(15000, response5.getBody().getObject().get("sum"));
    }

}