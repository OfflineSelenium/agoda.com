package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import com.web.coreframework.Common;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
        logInPage = webPageFactory.getLogInPage();
        EMAIL = testData.getProperty("email1");
        PASSWORD = testData.getProperty("password");

        logInPage = webPageFactory.loadLogInPage();
        myBookingsPage = logInPage.logInPassed(EMAIL1, PASSWORD);
        myBookingsPage.isLoaded();
    }

    @BeforeMethod
    public void beforeMethod() {
        myBookingsPage.load();
    }
	
	@Test(description = "VerifyDefaultMyBookingsPage")
    public void VerifyDefaultMyBookingsPage() {
        myBookingsPage.isLoaded();
        Assert.assertEquals(myBookingsPage.shouldSeeTextResultBookings(), "0 Total Bookings");
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
	
	@Test(description = "VerifyDefaultMyBookingsPage")
     public void VerifyDefaultMyBookingsPage() {
        myBookingsPage.isLoaded();
        Assert.assertEquals(myBookingsPage.shouldSeeTextResultBookings(), "0 Total Bookings");
    }
	
	 @Test(description = "VerifyDefaultMyReviewPage")
    public void VerifyDefaultMyReviewPage() {
        myBookingsPage.isLoaded();
        myBookingsPage.clickMyReviewsMenu();
        Assert.assertEquals(myBookingsPage.shouldSeeTextMessageMyReviews(), "You currently have no hotels to review.\n" +
                "Please visit us again after your departure date.");
    }

    @Test(description = "VerifyDefaultMyProfilePage")
    public void VerifyDefaultMyProfilePage() {
        myBookingsPage.isLoaded();
        myBookingsPage.clickMyProfileMenu();
        Assert.assertEquals(myBookingsPage.shouldSeeNameInMyProfile(), "Test Agoda");
        Assert.assertEquals(myBookingsPage.shouldSeeMailInMyProfile(), "test.secude@gmail.com");
    }
	
	@Test(description = "VerifyDefaultMyCardPage")
    public void VerifyDefaultMyCardPage() {
        myBookingsPage.isLoaded();
        myBookingsPage.clickMyCardDetails();
        Assert.assertEquals(myBookingsPage.shouldSeeNameInMyCardDetails(), "test.secude@gmail.com");
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
