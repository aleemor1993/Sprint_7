package org.example.order;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {

    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    protected final String ROOT = "/api/v1/orders";

    public ValidatableResponse create(Order order){
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .post(ROOT).then().log().all();
    }

    public ValidatableResponse getAll(){
        return given()
                .log().all()
                .baseUri(BASE_URI)
                .get(ROOT).then().log().all();
    }

    public ValidatableResponse get(int courierId){
        return given()
                .log().all()
                .baseUri(BASE_URI)
                .get(ROOT+"?courierId="+courierId).then().log().all();
    }
}
