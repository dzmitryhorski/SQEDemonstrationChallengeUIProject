package com.sample.services;

import com.sample.pages.InfoDialog;
import org.openqa.selenium.NoSuchElementException;

public class InfoDialogService {

    public void closeInfoDialog() {
        new InfoDialog().getCloseButton().click();
    }

    public boolean isDialogDisplayed() {
        try {
            return new InfoDialog().getCloseButton().isDisplayed();
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

}
