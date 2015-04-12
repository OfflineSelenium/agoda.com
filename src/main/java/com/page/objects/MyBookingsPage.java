package com.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

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

    @FindBy(xpath = "//a[@id ='ctl00_ctl00_MainContent_ContentMain_lbtTotalBooking']")
    private WebElement resultBookings;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyReview")
    private WebElement myReviews;

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_lblHotelReviewMessage")
    private WebElement getTextMyReviews;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyReview")
    private WebElement clickMyProfile;

    @FindBy(xpath = "//*[@id='udpBasicInfo']/div[2]/p[2]")
    private WebElement getName;

    @FindBy(xpath = "//*[@id='udpBasicInfo']/div[2]/p[4]")
    private WebElement getEmail;

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyCreditCards")
    private WebElement myCardDetails;

    @FindBy(xpath= "//*[@id='email']")
    private WebElement emailPopulated;

    public void load() {
        loadPage("/rewards/managebooking.html");
    }

    public void isLoaded(){
        webPageFactory.checkWeAreOnTheRightPage("My Bookings");
    }

    public LogInPage clickSignOut() {
        signOut.click();
        return webPageFactory.getLogInPage();
    }

    public String getResultSearch() {
        return resultBookings.getText();
    }

    public void clickMyReviewsMenu() {
        myReviews.click();
    }

    public String getTextMyReviews() {
       return getTextMyReviews.getText();
    }

    public void clickMyProfileMenu() {
        clickMyProfile.click();
    }

    public String getNameInMyProfile() {
        return getName.getText();
    }

    public String getEmailInMyProfile() {
        return getEmail.getText();
    }

    public void clickMyCardDetails() {
        myCardDetails.click();
    }

    public String getPopulateEmailInMyCardDetails() {
        return emailPopulated.getText();
    }
}
