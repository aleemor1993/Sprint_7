package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.junit.Ignore;
import org.junit.Test;
import org.example.steps.CourierSteps;

public class CourierTest {


    private final CourierGenerator generator = new CourierGenerator();

    private final CourierClient client = new CourierClient();

    private final CourierAssertions check = new CourierAssertions();

    private int courierId;

    private final CourierSteps steps = new CourierSteps();


    //Тесты на создание курьера

    @Test
    @DisplayName("Корректное создание курьера")
    public void createNewCourierAndLogin() {

        Courier courier = generator.random();

        courierId = check.loggedInSuccessfully(steps.courierCreateStep(client, check, courier));
    }

    @Test
    @DisplayName("Создание курьера без поля firstName")
    public void createNewCourierWithoutFirstName() {

        Courier courier = generator.random();
        courier.setFirstName(null);

        courierId = check.loggedInSuccessfully(steps.courierCreateStep(client, check, courier));
    }

    @Test
    @DisplayName("Создание курьера с пустым полем login")
    public void creationFailedEmptyLogin(){

        Courier courier = generator.random();
        courier.setLogin("");

        steps.courierCreateFailedStep(client, check, courier);
    }

    @Test
    @DisplayName("Создание курьера с отсутствием поля login")
    public void creationFailedNoLogin(){

        Courier courier = generator.random();
        courier.setLogin(null);

        steps.courierCreateFailedStep(client, check, courier);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем password")
    public void creationFailedEmptyPassword(){

        Courier courier = generator.random();
        courier.setPassword("");

        steps.courierCreateFailedStep(client, check, courier);
    }

    @Test
    @DisplayName("Создание курьера с отсутствием поля password")
    public void creationFailedNoPassword(){

        Courier courier = generator.random();
        courier.setPassword(null);

        steps.courierCreateFailedStep(client, check, courier);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    public void createTwoSameCouriers(){

        Courier courier = generator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        response = client.create(courier);
        check.loginIsUsed(response);
    }

    //Тесты на авторизацию

    @Test
    @DisplayName("Авторизация с корректными данными")
    public void loginIsSuccessful(){

        Courier courier = generator.generic();

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = client.login(credentials);
        check.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем")
    public void loginByNonExistingUser(){

        Courier courier = generator.random();

        steps.courierLoginFailed404(client, check, courier);

    }

    @Test
    @DisplayName("Авторизация без логина")
    public void loginFailedNoLogin(){

        Courier courier = generator.generic();
        courier.setLogin(null);

        steps.courierLoginFailed400(client, check, courier);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void loginFailedWithoutLogin(){

        Courier courier = generator.generic();
        courier.setPassword(null);

        steps.courierLoginFailed504(client, check, courier);
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    public void loginFailedEmptyLogin(){

        Courier courier = generator.generic();
        courier.setLogin("");

        steps.courierLoginFailed400(client, check, courier);
    }



    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void loginFailedEmptyPassword(){

        Courier courier = generator.generic();
        courier.setPassword("");

        steps.courierLoginFailed400(client, check, courier);
    }


    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void loginFailedWrongPassword(){

        Courier courier = generator.generic();
        courier.setPassword("1");

        steps.courierLoginFailed404(client, check, courier);
    }

    @Ignore
    public void deleteCourier(){
        if (courierId > 0){

            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }
}
