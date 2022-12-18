package org.example.courier;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {

    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    protected final String ROOT = "/api/v1/courier";

    public ValidatableResponse create(Courier courier){
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post(ROOT).then().log().all();
    }

    public ValidatableResponse login(Credentials credentials){
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(credentials)
                .when()
                .post(ROOT + "/login").then().log().all();

    }

    public ValidatableResponse delete(int courierId){
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId).then().log().all();

    }
}
