package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div[role='dialog']")
public class InfoDialog extends BasePage {

    @FindBy(css = "button[title='Close']")
    private WebElement closeButton;

    @FindBy(css = "[id='dialog'] p")
    private WebElement messageField;

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getMessageField() {
        return messageField;
    }

    public String getMessage() {
        return messageField.getText();
    }

}
