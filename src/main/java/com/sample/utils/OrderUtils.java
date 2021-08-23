package com.sample.utils;

import com.sample.enums.PaymentMethod;
import com.sample.enums.PizzaTopping;
import com.sample.enums.PizzaType;
import com.sample.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

public class OrderUtils {

    public static final String DEFAULT_PIZZA_SELECTION = "Choose Pizza";

    public static final String DEFAULT_FIRST_TOPPING = "Choose a Toppings 1";

    public static final String DEFAULT_SECOND_TOPPING = "Choose a Toppings 2";

    public static final String TEST_NAME = "Albert Einstein";

    public static final String TEST_EMAIL = "a.einstein@physics.com";

    public static final String TEST_PHONE = "375292220000";

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOG_MESSAGE = "Order created: ";


    public static Order initOrder(PizzaType pizzaType, BigInteger quantity, PaymentMethod paymentMethod) {
        Order order = initBasicOrder(pizzaType, quantity, paymentMethod).build();
        LOGGER.debug(LOG_MESSAGE + order.toString());
        return order;
    }

    public static Order initOrder(PizzaType pizzaType, BigInteger quantity, PizzaTopping firstTopping,
                                  PaymentMethod paymentMethod) {
        Order order =
                initBasicOrder(pizzaType, quantity, paymentMethod).with(Order::setFirstTopping, firstTopping).build();
        LOGGER.debug(LOG_MESSAGE + order.toString());
        return order;
    }

    public static Order initOrder(PizzaType pizzaType, BigInteger quantity, PizzaTopping firstTopping,
                                  PizzaTopping secondTopping, PaymentMethod paymentMethod) {
        Order order = initBasicOrder(pizzaType, quantity, paymentMethod)
                .with(Order::setFirstTopping, firstTopping)
                .with(Order::setSecondTopping, secondTopping)
                .build();
        LOGGER.debug(LOG_MESSAGE + order.toString());
        return order;
    }

    private static GenericBuilder<Order> initBasicOrder(PizzaType pizzaType, BigInteger quantity,
                                                        PaymentMethod paymentMethod) {
        return GenericBuilder.of(Order::new)
                .with(Order::setPizzaType, pizzaType)
                .with(Order::setQuantity, quantity)
                .with(Order::setName, TEST_NAME)
                .with(Order::setEmail, TEST_EMAIL)
                .with(Order::setPhone, TEST_PHONE)
                .with(Order::setPaymentMethod, paymentMethod);
    }

}
