package org.example.steps;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.Credentials;

public class CourierSteps {

   public ValidatableResponse courierCreateStep(CourierClient client, CourierAssertions check, Courier courier){

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = client.login(credentials);
        return loginResponse;

    }

    public void courierCreateFailedStep(CourierClient client, CourierAssertions check, Courier courier){

        ValidatableResponse response = client.create(courier);
        String message = check.creationFailed(response);
        assert !message.isBlank();

    }

    public void courierLoginFailed404(CourierClient client, CourierAssertions check, Courier courier){

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = client.login(credentials);
        check.loginFailed(loginResponse);

    }

    public void courierLoginFailed400(CourierClient client, CourierAssertions check, Courier courier){

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = client.login(credentials);
        check.loginFailedWhenEmptyField(loginResponse);

    }

    public void courierLoginFailed504(CourierClient client, CourierAssertions check, Courier courier){

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = client.login(credentials);
        check.loginFailedWithoutLogin(loginResponse);

    }
}
