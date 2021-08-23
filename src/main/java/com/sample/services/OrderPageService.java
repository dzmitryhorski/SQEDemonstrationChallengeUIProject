package com.sample.services;

import com.sample.enums.PaymentMethod;
import com.sample.enums.PizzaTopping;
import com.sample.enums.PizzaType;
import com.sample.models.Order;
import com.sample.pages.OrderPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.math.BigInteger;
import java.util.Objects;

public class OrderPageService {

    private static final Logger LOGGER = LogManager.getLogger();

    public void choosePizzaType(PizzaType type) {
        new OrderPage().getChoosePizzaDropdown().selectByValue(type.getDisplayName());
        LOGGER.debug("Pizza type set to {}.", type);
    }

    public void chooseFirstTopping(PizzaTopping topping) {
        new OrderPage().getFirstToppingsDropdown().selectByValue(topping.getDisplayName());
        LOGGER.debug("First topping set to {}.", topping);
    }

    public void resetFirstTopping() {
        new OrderPage().getFirstToppingsDropdown().selectByIndex(0);
        LOGGER.debug("First topping was reset.");
    }

    public void chooseSecondTopping(PizzaTopping topping) {
        new OrderPage().getSecondToppingsDropdown().selectByValue(topping.getDisplayName());
        LOGGER.debug("Second topping set to {}.", topping);
    }

    public void resetSecondTopping() {
        new OrderPage().getSecondToppingsDropdown().selectByIndex(0);
        LOGGER.debug("Second topping was reset.");
    }

    public void setQuantity(BigInteger qty) {
        WebElement qtyInput = new OrderPage().getQuantityInput();
        qtyInput.clear();
        qtyInput.sendKeys(qty.toString());
        LOGGER.debug("Quantity set to {}.", qty);
    }

    public void clickCostInput() {
        new OrderPage().getCostInput().click();
    }

    public void setName(String name) {
        new OrderPage().getNameInput().sendKeys(name);
        LOGGER.debug("Name set to {}.", name);
    }

    public void setEmail(String email) {
        new OrderPage().getEmailInput().sendKeys(email);
        LOGGER.debug("E-mail set to {}.", email);
    }

    public void setPhone(String phone) {
        new OrderPage().getPhoneInput().sendKeys(phone);
        LOGGER.debug("Phone number set to {}.", phone);
    }

    public void clickCashButton() {
        new OrderPage().getCashButton().click();
        LOGGER.debug("Cash on Pickup option selected.");
    }

    public void clickCreditCardButton() {
        new OrderPage().getCreditCardButton().click();
        LOGGER.debug("Credit Card option selected.");
    }

    public void clickPlaceOrderButton() {
        new OrderPage().getPlaceOrderButton().click();
        LOGGER.debug("Order was placed.");
    }

    public void clickResetButton() {
        new OrderPage().getResetButton().click();
        LOGGER.debug("Reset button was clicked.");
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CASH:
                clickCashButton();
                break;
            case CREDIT_CARD:
                clickCreditCardButton();
                break;
            default:
                throw new IllegalArgumentException(String.format("No such payment method %s.", paymentMethod));
        }
    }

    public void setFields(Order order) {
        choosePizzaType(order.getPizzaType());
        if (Objects.nonNull(order.getFirstTopping())) {
            chooseFirstTopping(order.getFirstTopping());
        }
        if (Objects.nonNull(order.getSecondTopping())) {
            chooseSecondTopping(order.getSecondTopping());
        }
        setQuantity(order.getQuantity());
        setName(order.getName());
        setEmail(order.getEmail());
        setPhone(order.getPhone());
        setPaymentMethod(order.getPaymentMethod());
    }

}
