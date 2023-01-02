package order;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.Order;
import org.example.order.OrderGenerator;
import org.example.order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {

    private final OrderClient client = new OrderClient();

    private final OrderGenerator orderGenerator = new OrderGenerator();

    @Parameterized.Parameter
    public final List<String> color = new ArrayList<>();

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] getParams() {
        return new Object[][] {
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK")},
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList()},
        };
    }

    @Test
    @DisplayName("Создание заказа с различными цветами")
    public void createOrder(){

        Order order = orderGenerator.generic();
        order.setColor(color);

        ValidatableResponse response = client.create(order);
        response.log().all().assertThat().body("track", notNullValue()).and().statusCode(201);

    }
}
