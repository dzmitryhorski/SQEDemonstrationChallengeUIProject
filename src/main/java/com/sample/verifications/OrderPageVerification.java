package com.sample.verifications;

import com.sample.enums.PizzaType;
import com.sample.models.Order;
import com.sample.pages.InfoDialog;
import com.sample.pages.OrderPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.sample.utils.OrderUtils.DEFAULT_FIRST_TOPPING;
import static com.sample.utils.OrderUtils.DEFAULT_PIZZA_SELECTION;
import static com.sample.utils.OrderUtils.DEFAULT_SECOND_TOPPING;

public class OrderPageVerification {

    private final SoftAssert softAssert = new SoftAssert();

    public void verifyCost(BigDecimal expectedCost) {
        BigDecimal actualCost = new OrderPage().getCostValue();
        Assert.assertEquals(actualCost, expectedCost);
    }

    public void verifyQuantity(BigInteger expectedQuantity) {
        BigInteger actualQuantity = new OrderPage().getQuantityValue();
        Assert.assertEquals(actualQuantity, expectedQuantity);
    }

    public void verifySingleRadioButtonIsSelected(boolean isCash) {
        OrderPage orderPage = new OrderPage();
        boolean isCashSelected = orderPage.getCashButton().isSelected();
        boolean isCreditCardSelected = orderPage.getCreditCardButton().isSelected();
        String errorMessage = getRadioButtonsErrorMessage(isCashSelected, isCreditCardSelected);
        Assert.assertTrue(isCashSelected == isCash && isCreditCardSelected == !isCash, errorMessage);
    }

    public void verifyInfoDialogErrorMessage(boolean isDialogPresent, String expectedErrorMessage) {
        Assert.assertTrue(isDialogPresent);
        String actualMessage = new InfoDialog().getMessage();
        Assert.assertEquals(actualMessage, expectedErrorMessage);
    }

    public void verifyResetState() {
        OrderPage orderPage = new OrderPage();
        String pizzaSelection = orderPage.getPizzaSelection();
        String firstTopping = orderPage.getFirstToppingSelection();
        String secondTopping = orderPage.getSecondToppingSelection();
        BigInteger quantity = orderPage.getQuantityValue();
        BigDecimal cost = orderPage.getCostValue();
        String name = orderPage.getName();
        String email = orderPage.getEmail();
        String phone = orderPage.getPhone();
        boolean isCashSelected = orderPage.getCashButton().isSelected();
        boolean isCreditCardSelected = orderPage.getCreditCardButton().isSelected();
        softAssert.assertEquals(pizzaSelection, DEFAULT_PIZZA_SELECTION);
        softAssert.assertEquals(firstTopping, DEFAULT_FIRST_TOPPING);
        softAssert.assertEquals(secondTopping, DEFAULT_SECOND_TOPPING);
        softAssert.assertEquals(quantity, BigInteger.ZERO);
        softAssert.assertEquals(cost, BigDecimal.ZERO);
        softAssert.assertEquals(name, StringUtils.EMPTY);
        softAssert.assertEquals(email, StringUtils.EMPTY);
        softAssert.assertEquals(phone, StringUtils.EMPTY);
        softAssert.assertFalse(isCashSelected);
        softAssert.assertFalse(isCreditCardSelected);
        softAssert.assertAll();
    }

    public void verifySuccessMessage(Order order) {
        String actualMessage = new InfoDialog().getMessage();
        String expectedMessage = getSuccessMessage(order);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    private String getSuccessMessage(Order order) {
        String successMessageTitle = "Thank you for your order! TOTAL: ";
        BigDecimal qty = BigDecimal.valueOf(order.getQuantity().intValue());
        PizzaType pizzaType = order.getPizzaType();
        BigDecimal totalCost = pizzaType.getCost().multiply(qty);
        return new StringBuilder().append(successMessageTitle)
                .append(totalCost.stripTrailingZeros().toPlainString()).append(StringUtils.SPACE)
                .append(pizzaType.getDisplayName()).toString();
    }

    private String getRadioButtonsErrorMessage(boolean isCashSelected, boolean isCreditCardSelected) {
        String cashSelectionState = isCashSelected ? StringUtils.EMPTY : "not ";
        String creditCardSelectionState = isCreditCardSelected ? StringUtils.EMPTY : "not ";
        return String.format("Cash option is %sselected. Credit Card option is %sselected.",
                cashSelectionState, creditCardSelectionState);
    }

}
