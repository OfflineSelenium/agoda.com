package com.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPasswordSignInPage extends GeneralPage {

    @FindBy(id = "password")
    private WebElement enterPassWord;

    @FindBy(id = "signin-btn")
    private WebElement signInButton;

    public void load() {
        loadPage("/index.html?resulturl=/creditcarddetail.html&loginreason=1&");
    }

    public void isLoad() {
        webPageFactory.checkWeAreOnTheRightPage("My Booking");
    }

    public ConfirmPasswordSignInPage enterPasswordAgian(String password) {
        enterPassWord.sendKeys(password);
        return this;
    }

    public MyBookingsPage clickSignIn() {
        signInButton.click();
        return webPageFactory.getMyBookingsPage();
    }

    public MyBookingsPage confirmLoginAgian(String password) {
        return enterPasswordAgian(password).clickSignIn();
    }
}
