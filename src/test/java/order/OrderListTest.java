package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.example.order.OrderClient;
import org.junit.Test;

public class OrderListTest {

    private final OrderClient orderClient = new OrderClient();

    private final CourierGenerator courierGenerator = new CourierGenerator();

    private final CourierClient courierClient = new CourierClient();

    private final CourierAssertions checkCourier = new CourierAssertions();

    @Test
    @DisplayName("Получение списка всех заказов")
    public void getListOfAllOrders(){

        ValidatableResponse response = orderClient.getAll();
        response.assertThat().statusCode(200).extract().path("orders");

    }

    @Test
    @DisplayName("Получение списка заказов одного пользователя")
    public void getListOfOneUser(){

        Courier courier = courierGenerator.generic();

        Credentials credentials = new Credentials().from(courier);
        ValidatableResponse loginResponse = courierClient.login(credentials);
        int courierId = checkCourier.loggedInSuccessfully(loginResponse);

        ValidatableResponse response = orderClient.get(courierId);
        response.assertThat().statusCode(200).extract().path("orders");

    }
}
