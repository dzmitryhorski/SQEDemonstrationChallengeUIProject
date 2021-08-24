package com.sample.test.demo.tests;

import com.google.common.collect.Lists;
import com.sample.basetest.BaseTest;
import com.sample.enums.PaymentMethod;
import com.sample.enums.PizzaTopping;
import com.sample.enums.PizzaType;
import com.sample.models.Order;
import com.sample.utils.OrderUtils;
import org.apache.commons.lang3.function.TriFunction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sample.enums.PizzaType.LARGE_NOTOPPINGS;
import static com.sample.enums.PizzaType.LARGE_TWOTOPPINGS;
import static com.sample.enums.PizzaType.MEDIUM_TWOTOPPINGS;
import static com.sample.enums.PizzaType.SMALL_NOTOPPINGS;
import static com.sample.enums.PizzaType.SMALL_ONETOPPING;

public class EndToEndTest extends BaseTest {

    @DataProvider
    public Object[][] noToppings() {
        List<PizzaType> noToppingsTypes = Lists.newArrayList(SMALL_NOTOPPINGS, LARGE_NOTOPPINGS);
        List<Order> orders = new ArrayList<>();
        Arrays.stream(PaymentMethod.values()).forEach(paymentMethod -> {
            noToppingsTypes.forEach(topping -> {
                BigInteger randomQuantity = randomQuantity();
                Order order = OrderUtils.initOrder(topping, randomQuantity, paymentMethod);
                orders.add(order);
            });
        });
        return orders.stream().map(order -> new Object[] {order}).toArray(Object[][]::new);
    }

    @Test(dataProvider = "noToppings", description = "Verify correct info is displayed in info dialog after order for" +
            " a pizza with no toppings is placed")
    public void successfulNoToppingsOrderInfoTest(Order order) {
        placeOrder(order);
        orderPageVerification.verifySuccessMessage(order);
    }

    @DataProvider
    public Object[][] oneTopping() {
        List<Order> orders = new ArrayList<>();
        Arrays.stream(PaymentMethod.values()).forEach(paymentMethod ->
                Arrays.stream(PizzaTopping.values()).forEach(topping -> {
                    BigInteger randomQuantity = randomQuantity();
                    Order order = OrderUtils.initOrder(SMALL_ONETOPPING, randomQuantity, topping, paymentMethod);
                    orders.add(order);
        }));
        return orders.stream().map(order -> new Object[] {order}).toArray(Object[][]::new);
    }

    @Test(dataProvider = "oneTopping", description = "Verify correct info is displayed in info dialog after order for" +
            " a pizza with one topping is placed")
    public void successfulOneToppingOrderInfoTest(Order order) {
        placeOrder(order);
        orderPageVerification.verifySuccessMessage(order);
    }

   @DataProvider
    public Object[][] twoToppings() {
        List<PizzaTopping> firstToppings = Arrays.asList(PizzaTopping.values());
        List<PizzaTopping> secondToppings = Lists.reverse(firstToppings);
        List<PizzaType> pizzaTypes = Lists.newArrayList(MEDIUM_TWOTOPPINGS, LARGE_TWOTOPPINGS);
        List<Order> orders = new ArrayList<>();
        TriFunction<PizzaType, Integer, PaymentMethod, Order> createOrder = (pizzaType, i, paymentMethod) -> {
            BigInteger randomQuantity = randomQuantity();
            return OrderUtils.initOrder(pizzaType, randomQuantity, firstToppings.get(i), secondToppings.get(i),
                    paymentMethod);
        };
        Arrays.asList(PaymentMethod.values()).forEach(paymentMethod -> {
            pizzaTypes.forEach(pizzaType -> {
                List<Order> currentOrders = IntStream.range(0, firstToppings.size())
                        .mapToObj(i -> createOrder.apply(pizzaType, i, paymentMethod)).collect(Collectors.toList());
                orders.addAll(currentOrders);
            });
        });
       return orders.stream().map(order -> new Object[] {order}).toArray(Object[][]::new);
    }

    @Test(dataProvider = "twoToppings", description = "Verify correct info is displayed in info dialog after order for" +
            " a pizza with two toppings is placed")
    public void successfulTwoToppingsOrderInfoTest(Order order) {
        placeOrder(order);
        orderPageVerification.verifySuccessMessage(order);
    }

}
