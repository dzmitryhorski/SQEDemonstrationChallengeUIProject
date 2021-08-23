package com.sample.test.demo.tests;

import com.sample.enums.PizzaTopping;
import com.sample.enums.PizzaType;
import com.sample.basetest.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Consumer;

import static com.sample.utils.OrderUtils.TEST_EMAIL;
import static com.sample.utils.OrderUtils.TEST_NAME;
import static com.sample.utils.OrderUtils.TEST_PHONE;

public class IntegrationTest extends BaseTest {

    private static final BigInteger TEN_THOUSAND = BigInteger.valueOf(10000);

    private static final BigInteger HUNDRED_THOUSAND = BigInteger.valueOf(100000);

    private static final String MISSING_NAME_ERROR = "Missing name";

    private static final String MISSING_PHONE_ERROR = "Missing phone number";

    private static final String MISSING_NAME_AND_PHONE_MESSAGE =
            MISSING_NAME_ERROR + StringUtils.LF + MISSING_PHONE_ERROR;

    @DataProvider
    public Object[][] quantities() {
        return new Object[][] {
                {BigInteger.ONE.negate(), BigDecimal.ZERO},
                {BigInteger.ZERO, BigDecimal.ZERO},
                {BigInteger.ONE, PizzaType.SMALL_NOTOPPINGS.getCost()}};
    }

    @Test(dataProvider = "quantities",
            description = "Verify cost is displayed correctly based on pizza selection and quantity")
    public void costOfQuantityTest(BigInteger qty, BigDecimal expectedCost) {
        orderPageService.choosePizzaType(PizzaType.SMALL_NOTOPPINGS);
        orderPageService.setQuantity(qty);
        orderPageService.clickCostInput();
        orderPageVerification.verifyCost(expectedCost);
    }

    @DataProvider
    public Object[][] pizzaTypes() {
        return Arrays.stream(PizzaType.values()).map(pizzaType -> new Object[]{pizzaType, pizzaType.getCost()})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "pizzaTypes",
            description = "Verify cost is displayed correctly based on pizza selection and quantity")
    public void costOfPizzaTypeTest(PizzaType pizzaType, BigDecimal expectedCost) {
        orderPageService.choosePizzaType(pizzaType);
        orderPageService.setQuantity(BigInteger.ONE);
        orderPageService.clickCostInput();
        orderPageVerification.verifyCost(expectedCost);
    }

    @Test(description = "Verify number longer than 5 digits is not allowed in Quantity input")
    public void maxDigitsInQuantityInputTest() {
        orderPageService.setQuantity(HUNDRED_THOUSAND);
        orderPageVerification.verifyQuantity(TEN_THOUSAND);
    }

    @Test(description = "Verify only one radio button can be selected at a time")
    public void radioButtonsTest() {
        orderPageService.clickCashButton();
        orderPageService.clickCreditCardButton();
        orderPageVerification.verifySingleRadioButtonIsSelected(false);
    }

    @Test(description = "Verify error message appears if mandatory fields are not filled out")
    public void errorMessageNoFieldsFilledTest() {
        orderPageService.clickPlaceOrderButton();
        boolean isDialogPresent = infoDialogService.isDialogDisplayed();
        orderPageVerification.verifyInfoDialogErrorMessage(isDialogPresent, MISSING_NAME_AND_PHONE_MESSAGE);
    }

    @DataProvider
    public Object[] mandatoryFields() {
        Consumer<String> setName = name -> orderPageService.setName(name);
        Consumer<String> setPhone = phone -> orderPageService.setPhone(phone);
        return new Object[][]{
                {setName, TEST_NAME, MISSING_PHONE_ERROR},
                {setPhone, TEST_PHONE, MISSING_NAME_ERROR}};
    }

    @Test(dataProvider = "mandatoryFields",
            description = "Verify error message appears if one mandatory field is not filled out")
    public void errorMessageOneFieldFilledTest(Consumer<String> setField, String data, String expectedMessage) {
        orderPageService.clickResetButton();
        setField.accept(data);
        orderPageService.clickPlaceOrderButton();
        boolean isDialogPresent = infoDialogService.isDialogDisplayed();
        orderPageVerification.verifyInfoDialogErrorMessage(isDialogPresent, expectedMessage);
    }

    @Test(description = "Verify all fields have default or no values after reset")
    public void resetButtonTest() {
        fillAllFields();
        orderPageService.clickResetButton();
        orderPageVerification.verifyResetState();
    }

    private void fillAllFields() {
        orderPageService.choosePizzaType(PizzaType.MEDIUM_TWOTOPPINGS);
        orderPageService.chooseFirstTopping(PizzaTopping.DICED_MANGO);
        orderPageService.chooseSecondTopping(PizzaTopping.CARAMELIZED_ONIONS);
        orderPageService.setQuantity(BigInteger.ONE);
        orderPageService.setName(TEST_NAME);
        orderPageService.setEmail(TEST_EMAIL);
        orderPageService.setPhone(TEST_PHONE);
        orderPageService.clickCreditCardButton();
    }

}
