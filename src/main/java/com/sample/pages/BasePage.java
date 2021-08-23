package com.sample.pages;

import com.sample.utils.WebDriverProvider;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    BasePage() {
        PageFactory.initElements(WebDriverProvider.getDriver(), this);
    }

}
