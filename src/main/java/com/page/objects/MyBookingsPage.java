package com.page.objects;

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

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyCreditCards")
    private WebElement linkMyCardDetails;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyProfile")
    private WebElement linkMyProfile;

    @FindBy(id = "lbtEditBasicInfo")
    private WebElement linkEditInformation;

    @FindBy(id = "lbtSaveBasicInfo")
    private WebElement linkSaveInformation;

    @FindBy(id = "password")
    private WebElement enterPassWord;

    @FindBy(id = "signin-btn")
    private WebElement signInButton;

    @FindBy(id = "ShowAddCardDivButton")
    private WebElement ShowAddCardDivButton;

    @FindBy(id = "ddlCountryOfPassport")
    private WebElement ChooseCountry;

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

    public MyBookingsPage clickMyProfile() {
        linkMyProfile.click();
        return this;
    }

    public MyBookingsPage clickMyCardDetails() {
        linkMyCardDetails.click();
//        webElementFinder.waitElementLocatorLoaded(By.id("password"));
        return this;
    }

    public MyBookingsPage clickLinkEditInformation() {
        linkEditInformation.click();
        return this;
    }

    public MyBookingsPage clickLinkSaveInformation() {
        linkSaveInformation.click();
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

    public boolean verifyShouldDisplayAddNewCardButton() {
        return ShowAddCardDivButton.isDisplayed();
    }

    public Boolean shouldSeeTextNotificationError(String text) {
        String xpath = "//*[@id='basicSummaryError']/ul/li[1]";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public MyBookingsPage chooseCountryOfPassport(String country) {
        ChooseCountry.sendKeys(country);
        return this;
    }

    public String shouldSeeTextSelectedCountryDropdown() {
//        String css = "select#ddlCountryOfPassport option[value='199']";
        String xpath = "//select[@id='ddlCountryOfPassport']/option[@selected='selected']";
        return webElementFinder.findElementByLocatorXPath(xpath).getText();
    }

    public String shouldSeeTextBasicInformationUpdateSuccessfully() {
        return webElementFinder.findElementByLocatorXPath("//span[contains(@class,'text_green')]").getText();
    }
}

