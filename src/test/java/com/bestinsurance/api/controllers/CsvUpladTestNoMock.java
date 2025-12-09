package com.bestinsurance.api.controllers;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CsvUpladTestNoMock {

    @Test
    public void testSize() throws IOException {
        ClassPathResource resource = new ClassPathResource("customers_toobig.csv");
        RestAssured.baseURI = "http://localhost:7777/best_insurance/subscriptions/upload";
        RestAssured.port = 7777;
        //Response res =
                given()
                .multiPart("data", resource.getFile(), "text/plain")
                .when().post("/upload").then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
