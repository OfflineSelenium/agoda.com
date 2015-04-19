package com.page.objects;

import com.web.coreframework.WebElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyReview")
    private WebElement myReviewsMenu;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyReview")
    private WebElement myProfileMenu;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyCreditCards")
    private WebElement myCardDetails;

    @FindBy(xpath = "//*[@id='ctl00_ctl00_MainContent_leftMenu1_udpMenu']//ul//li")
    private List<WebElement> panelItems;

    @FindBy(xpath = "//*[contains(@class,'header')]//h1")
    private WebElement textHeader;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyCreditCards")
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

    public void clickMyReviewsMenu() {
        myReviewsMenu.click();
    }

    public void clickMyProfileMenu() {
        myProfileMenu.click();
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
        return this;
    }

    public MyBookingsPage enterPasswordAgain(String password) {
        enterPassWord.sendKeys(password);
        return this;
    }

    public MyBookingsPage clickSignIn() {
        signInButton.click();
        return this;
    }

    public MyBookingsPage confirmLoginAgian(String password) {
        return enterPasswordAgain(password).clickSignIn();
    }

    public LogInPage clickSignOutOnTop() {
        signOutOnTop.click();
        return webPageFactory.getLogInPage();
    }

    public boolean verifyShouldDisplayAddNewCardButton() {
        return ShowAddCardDivButton.isDisplayed();
    }

    public String shouldSeeTextResultBookings() {
        String xpath = "//a[@id ='ctl00_ctl00_MainContent_ContentMain_lbtTotalBooking']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }

    public String shouldSeeTextMessageMyReviews() {
        String id = "ctl00_ctl00_MainContent_ContentMain_lblHotelReviewMessage";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        return element.getText();
    }

    public String shouldSeeNameInMyProfile() {
        String xpath = "//*[@id='udpBasicInfo']/div[2]/p[2]";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }

    public String shouldSeeMailInMyProfile() {
        String xpath = "//*[@id='udpBasicInfo']/div[2]/p[4]";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }

    public String shouldSeeNameInMyCardDetails() {
        String xpath = "//*[@id='email']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }
}

