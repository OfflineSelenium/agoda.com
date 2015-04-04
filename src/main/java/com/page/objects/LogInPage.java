package com.page.objects;

import com.web.coreframework.Common;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LogInPage extends GeneralPage {

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_txtEmail")
    private WebElement txtEmail;

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_txtPassword")
    private WebElement txtPassword;

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_btnSignIn")
    private WebElement btnSignIn;

    public void load() {
        loadPage("/rewards/login.html");
    }

    public void isLoaded() {
        webPageFactory.checkWeAreOnTheRightPage("Sign In");
    }

    public LogInPage enterEmail(String email) {
        txtEmail.sendKeys(email);
        return this;
    }

    public LogInPage enterPassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public MyBookingsPage clickSignInPassed() {
        btnSignIn.click();
        return webPageFactory.getMyBookingsPage();
    }

    public LogInPage clickSignInFailed() {
        btnSignIn.click();
        return this;
    }

    public String signInNotificationText() {
        return findElementByLocator("notification_sign_in").getText();
    }

//    public MyBookingsPage logInPassed(String email, String password) {
//        return enterEmail(email).enterPassword(password).clickSignInPassed();
//    }

    public boolean seeRedErrorMsgEmailRequired(String text) {
        String id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_rfvEmail";
        WebElement element = webElementFinder.findElementByLocatorID(id);

        if (!element.getText().equals(text))
            return false;

        String color = element.getCssValue("color");
        return Common.convertToHex(color).equals("#ff0000");
    }

    // Thach
    public String signInNotificationTextEmail() {
        return findElementByLocator("ctl00_ctl00_MainContent_ContentMain_RewardLogin1_revEmail").getText();
    }
    public String signInNotificationTextPassword() {
        return findElementByLocator("ctl00_ctl00_MainContent_ContentMain_RewardLogin1_rfvPassword").getText();
    }
    public String signInNotificationTextRequiredEmail() {
        return findElementByLocator("ctl00_ctl00_MainContent_ContentMain_RewardLogin1_rfvEmail").getText();
    }

}
