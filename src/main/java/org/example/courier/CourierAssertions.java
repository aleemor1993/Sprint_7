package org.example.courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierAssertions {

    public void createdSuccessfully(ValidatableResponse response){

        response.log().all().assertThat().body("ok", equalTo(true)).and().statusCode(201);

    }

    public int loggedInSuccessfully(ValidatableResponse loginResponse){

        return loginResponse.log().all().assertThat().statusCode(200).extract().path("id");

    }

    public String creationFailed(ValidatableResponse response){

        return response.assertThat().statusCode(400).body("message",notNullValue()).extract().path("message");

    }

    public void loginFailed(ValidatableResponse loginResponse) {

        loginResponse.log().all().assertThat().statusCode(404);

    }

    public void deletedSuccessfully(ValidatableResponse response){

        response.log().all().assertThat().body("ok", equalTo(true)).and().statusCode(200);

    }

    public String loginIsUsed(ValidatableResponse response){

        return response.assertThat().statusCode(409).body("message",notNullValue()).extract().path("message");

    }

    public String loginFailedWhenEmptyField(ValidatableResponse response){

        return response.assertThat().statusCode(400).body("message",notNullValue()).extract().path("message");

    }

    public void loginFailedWithoutLogin(ValidatableResponse response){

        response.assertThat().statusCode(504);

    }
}
