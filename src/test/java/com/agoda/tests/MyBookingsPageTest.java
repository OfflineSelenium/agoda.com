package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import com.web.coreframework.Common;
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

//    @AfterMethod
//    public void afterMethod() {
//        myBookingsPage.clickSignOutOnTop();
//    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "Verify Show Profile")
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

//    @Test(description = "verify Text Header")
//    public void verifyShowTextHeader() {
//        // Verify the displayed correct page
//        myBookingsPage.clickMyCardDetails();
//        Common.sleep(5000);
//        myBookingsPage.confirmLoginAgian(PASSWORD);
//
//        Assert.assertTrue(myBookingsPage.shouldDisplayTextHeader("My Card Details"));
//        Assert.assertTrue(myBookingsPage.verifyShouldDisplayAddNewCardButton());
//    }

}
