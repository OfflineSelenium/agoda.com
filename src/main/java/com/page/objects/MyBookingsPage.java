package com.page.objects;

import com.web.coreframework.Common;
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

    @FindBy(xpath = "//div[@id='ctl00_ctl00_MainContent_leftMenu1_udpMenu']//ul//li")
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

   
    public boolean shouldDisplayEmailLogin(String text) {
        String xpath = "//div[@class= 'account_email']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public String verifyLeftPanelItems(int i) {
        return panelItems.get(i).getText();
    }

    public boolean shouldDisplayTextHeader(String text) {
        return textHeader.getText().equals(text);
    }

    public ConfirmPasswordSignInPage clickMyCardDetailsFirstTime() {
        linkMyCardDetails.click();
        return webPageFactory.getConfirmPasswordSignInPage();
    }

    public MyBookingsPage clickMyCardDetails() {
        linkMyCardDetails.click();
        return this;
    }

    public LogInPage clickSignOutOnTop() {
        signOutOnTop.click();
        return webPageFactory.getLogInPage();
    }

    public boolean verifyShouldDisplayAddNewCardButton() {
        return ShowAddCardDivButton.isDisplayed();
    }

    public boolean shouldDisplayButtonSave() {
        String xpath = "//input[@id='KeepCardSaveButton']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.isDisplayed();
    }

    public boolean shouldDisplayCheckbox() {
        String id = "KeepCard";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        return element.isDisplayed();
    }
	
  public String shouldSeeUpComing() {
        String xpath = "//a[@id='ctl00_ctl00_MainContent_ContentMain_lbtUpcomingBooking']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }

  public String shouldSeeDeparted() {
        String xpath = "//a[@id = 'ctl00_ctl00_MainContent_ContentMain_lbtDepartBooking']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }
    public boolean defaultValueOfCheckboxShouldUncheck() {
        String id = "KeepCard";
        boolean isSelected = webElementFinder.findElementByLocatorID(id).isSelected();
        if(!isSelected) {
            return true;
        }
        return false;
    }
	
	 public String shouldSeeCancelled() {
        String xpath = "//a[@id='ctl00_ctl00_MainContent_ContentMain_lbtCancelledBooking']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }

    public String shouldDisplayEmailLoginText() {
        String xpath = "//div[@class= 'account_email']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText();
    }


    public String shouldSeeNameColor() {
        String xpath = "//div/p[@class = 'text_account']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        String col = element.getCssValue("color");
        return Common.convertToHex(col);
    }
}

