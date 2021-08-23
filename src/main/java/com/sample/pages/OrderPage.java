package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderPage extends BasePage {

    private static final String VALUE_ATTRIBUTE = "value";

    @FindBy(id = "pizza1pizza")
    private WebElement choosePizzaDropdown;

    @FindBy(css = "select[class='toppings1']")
    private WebElement firstToppingsDropdown;

    @FindBy(css = "select[class='toppings2']")
    private WebElement secondToppingsDropdown;

    @FindBy(id = "pizza1Qty")
    private WebElement quantityInput;

    @FindBy(id = "pizza1Cost")
    private WebElement costInput;

    @FindBy(id = "ccpayment")
    private WebElement creditCardButton;

    @FindBy(id = "cashpayment")
    private WebElement cashButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "placeOrder")
    private WebElement placeOrderButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    public Select getChoosePizzaDropdown() {
        return new Select(choosePizzaDropdown);
    }

    public String getPizzaSelection() {
        return getChoosePizzaDropdown().getFirstSelectedOption().getText();
    }

    public Select getFirstToppingsDropdown() {
        return new Select(firstToppingsDropdown);
    }

    public String getFirstToppingSelection() {
        return getFirstToppingsDropdown().getFirstSelectedOption().getText();
    }

    public Select getSecondToppingsDropdown() {
        return new Select(secondToppingsDropdown);
    }

    public String getSecondToppingSelection() {
        return getSecondToppingsDropdown().getFirstSelectedOption().getText();
    }

    public WebElement getQuantityInput() {
        return quantityInput;
    }

    public BigInteger getQuantityValue() {
        String quantityValue = quantityInput.getAttribute(VALUE_ATTRIBUTE);
        return new BigInteger(quantityValue);
    }

    public WebElement getCostInput() {
        return costInput;
    }

    public BigDecimal getCostValue() {
        String costValue = costInput.getAttribute(VALUE_ATTRIBUTE);
        return new BigDecimal(costValue);
    }

    public WebElement getCreditCardButton() {
        return creditCardButton;
    }

    public WebElement getCashButton() {
        return cashButton;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public String getEmail() {
        return emailInput.getText();
    }

    public WebElement getNameInput() {
        return nameInput;
    }

    public String getName() {
        return nameInput.getText();
    }

    public WebElement getPhoneInput() {
        return phoneInput;
    }

    public String getPhone() {
        return phoneInput.getText();
    }

    public WebElement getPlaceOrderButton() {
        return placeOrderButton;
    }

    public WebElement getResetButton() {
        return resetButton;
    }

}
