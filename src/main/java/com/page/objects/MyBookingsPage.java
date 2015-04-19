package com.page.objects;

import com.web.coreframework.Common;
import com.web.coreframework.WebElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyBookingsPage extends GeneralPage {

    @FindBy(id = "leftpage")
    private WebElement leftPanel;

    @FindBy(className = "text_account")
    private WebElement accountName;

    @FindBy(className = "account_email")
    private WebElement accountEmail;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtSignOut")
    private WebElement signOut;

    @FindBy(xpath = "//*[@id='ctl00_ctl00_MainContent_leftMenu1_udpMenu']//ul//li")
    private List<WebElement> panelItems;

    @FindBy(xpath = "//*[contains(@class,'header')]//h1")
    private WebElement textHeader;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_MyCreditCardsMenuItem")
    private WebElement linkMyCardDetails;

    @FindBy(id = "password")
    private WebElement enterPassWord;

    @FindBy(id = "signin-btn")
    private WebElement signInButton;

    @FindBy(xpath = "//a[.='Sign Out']")
    private WebElement signOutOnTop;

    @FindBy(id = "ShowAddCardDivButton")
    private WebElement ShowAddCardDivButton;

    public void load() {
        loadPage("/rewards/managebooking.html");
    }

    public void isLoaded() {
        webPageFactory.checkWeAreOnTheRightPage("My Bookings");
    }

    public LogInPage clickSignOut() {
        signOut.click();
        return webPageFactory.getLogInPage();
    }

    public boolean shouldDisplayEmailLogin(String text) {
        String xpath = "//*[contains(@class,'account_email')]";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public String verifyLeftPanelItems(int i) {
        return panelItems.get(i).getText();
    }

    public boolean shouldDisplayTextHeader(String text) {
        return textHeader.getText().equals(text);
    }

    public MyBookingsPage clickMyCardDetails() {
        linkMyCardDetails.click();
        webElementFinder.waitElementLocatorLoaded(By.id("password"));
        return this;
    }

    public MyBookingsPage enterPasswordAgian(String password) {
        enterPassWord.sendKeys(password);
        return this;
    }

    public MyBookingsPage clickSignIn() {
        signInButton.click();
        return this;
    }

    public MyBookingsPage confirmLoginAgian(String password) {
        return enterPasswordAgian(password).clickSignIn();
    }

    public LogInPage clickSignOutOnTop() {
        signOutOnTop.click();
        return webPageFactory.getLogInPage();
    }

    public boolean verifyShouldDisplayAddNewCardButton() {
        return ShowAddCardDivButton.isDisplayed();
    }

    public boolean shouldDisplayButtonSave() {
        String xpath="//input[@id='KeepCardSaveButton']";
        WebElement element= webElementFinder.findElementByLocatorXPath(xpath);
        return element.isDisplayed();
    }
}

