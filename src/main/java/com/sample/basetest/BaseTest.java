package com.sample.basetest;

import com.google.inject.Inject;
import com.sample.configuration.Configuration;
import com.sample.models.Order;
import com.sample.services.InfoDialogService;
import com.sample.services.OrderPageService;
import com.sample.utils.ConfigurationModule;
import com.sample.utils.WebDriverProvider;
import com.sample.verifications.OrderPageVerification;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

import java.math.BigInteger;
import java.util.Random;

@Guice(modules = ConfigurationModule.class)
public class BaseTest {

    @Inject
    protected OrderPageService orderPageService;

    @Inject
    protected InfoDialogService infoDialogService;

    @Inject
    protected OrderPageVerification orderPageVerification;

    protected String url;

    protected Logger LOGGER;

    @BeforeClass(alwaysRun = true)
    public void init() {
        url = new Configuration().getUrl();
        WebDriverProvider.getDriver().get(url);
        LOGGER = Logger.getLogger(this.getClass());
        BasicConfigurator.configure();
    }

    @BeforeMethod
    public void reset() {
        orderPageService.clickResetButton();
        orderPageService.resetFirstTopping();
        orderPageService.resetSecondTopping();
        if (infoDialogService.isDialogDisplayed()) {
            infoDialogService.closeInfoDialog();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverProvider.getDriver().quit();
    }

    protected void placeOrder(Order order) {
        orderPageService.setFields(order);
        orderPageService.clickPlaceOrderButton();
    }

    protected BigInteger randomQuantity() {
        Random random = new Random();
        return BigInteger.valueOf(random.nextInt(1000 - 1) + 1);
    }

}
