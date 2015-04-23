package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyBookingsPageTest extends BasePage {

    private MyBookingsPage myBookingsPage;
    private LogInPage logInPage;

    private String EMAIL1;
    private String PASSWORD;

    @BeforeClass
    public void beforeClass() {
        EMAIL1 = testData.getProperty("email1");
        PASSWORD = testData.getProperty("password");

        logInPage = webPageFactory.loadLogInPage();
        myBookingsPage = logInPage.logInPassed(EMAIL1, PASSWORD);
        myBookingsPage.isLoaded();
    }

    @BeforeMethod
    public void beforeMethod() {
        myBookingsPage.load();
    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "verify Email Login Show Correct")
    public void verifyEmailLoginShowCorrect() {
        //verify profile name
        Assert.assertTrue(myBookingsPage.shouldDisplayEmailLogin(EMAIL1));
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(0), "My Bookings");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(1), "My Reviews");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(2), "My Profile");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(3), "My Card Details");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(4), "Change Password");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(5), "Refer a Friend");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(6), "Sign Out");
    }

    @Test(description = "verify Show Text Header")
    public void verifyShowTextHeader() {
        // Verify the displayed correct page
        myBookingsPage.clickMyCardDetails()
                .confirmLoginAgian(PASSWORD);

        Assert.assertTrue(myBookingsPage.shouldDisplayTextHeader("My Card Details"));
        Assert.assertTrue(myBookingsPage.verifyShouldDisplayAddNewCardButton());
    }

    @Test(description = "verify Update Information Profile Incorrect")
    public void verifyUpdateInformationProfileIncorrect() {
//        myBookingsPage.isLoaded();
        myBookingsPage.clickMyProfile()
                      .clickLinkEditInformation()
                      .chooseCountryOfPassport("Please Select")
                      .clickLinkSaveInformation();

        Assert.assertTrue(myBookingsPage.shouldSeeTextNotificationError("Basic Information is incorrect."));
    }

    @Test(description = "verify Update Information Successfully")
    public void verifyUpdateInformationSuccessfully() {
        myBookingsPage.isLoaded();
        myBookingsPage.clickMyProfile()
                      .clickLinkEditInformation()
                      .chooseCountryOfPassport("Vietnam")
                      .clickLinkSaveInformation();

        System.out.println(myBookingsPage.shouldSeeTextSelectedCountryDropdown());

        //verify information has been changed
        Assert.assertEquals(myBookingsPage.shouldSeeTextSelectedCountryDropdown(), "Vietnam");
//        Assert.assertEquals(myBookingsPage.shouldSeeTextBasicInformationUpdateSuccessfully(), "Your Basic Information has been changed successfully.");

//      //Clean up data
//        myBookingsPage.clickMyProfile()
//                      .clickLinkEditInformation()
//                      .chooseCountryOfPassport("United Kingdom")
//                      .clickLinkSaveInformation();
    }
}
