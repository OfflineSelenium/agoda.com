package com.agoda.tests;

import com.page.objects.ConfirmPasswordSignInPage;
import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyBookingsPageTest extends BasePage {

    private MyBookingsPage myBookingsPage;
    private LogInPage logInPage;
    private ConfirmPasswordSignInPage confirmPasswordSignInPage;

    private String EMAIL1;
    private String PASSWORD;
    private final String COLOR_BLUE = "#0283df";

    @BeforeClass
    public void beforeClass() {
        EMAIL1 = testData.getProperty("email1");
        PASSWORD = testData.getProperty("password");

        logInPage = webPageFactory.loadLogInPage();
        myBookingsPage = logInPage.logInPassed(EMAIL1, PASSWORD);
        myBookingsPage.isLoaded();

        //Confirm password when enter My Card Details
        confirmPasswordSignInPage = myBookingsPage.clickMyCardDetailsFirstTime();
        myBookingsPage = confirmPasswordSignInPage.enterPasswordAgian(PASSWORD).clickSignIn();
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

    @Test(description = "My Bookings-NoCriteriaData-VerifyDedaultPageDisplayed")
    public void verifyDefaultMyBookingsPageDisplayWithNoCriteriaData() {
        Assert.assertEquals(myBookingsPage.shouldSeeNameColor(), COLOR_BLUE);
        Assert.assertEquals(myBookingsPage.shouldDisplayEmailLoginText(), EMAIL1);
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(0), "My Bookings");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(1), "My Reviews");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(2), "My Profile");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(3), "My Card Details");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(4), "Change Password");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(5), "Refer a Friend");
        Assert.assertEquals(myBookingsPage.verifyLeftPanelItems(6), "Sign Out");
        Assert.assertEquals(myBookingsPage.shouldSeeTextResultBookings(), "0 Total Bookings");
        Assert.assertEquals(myBookingsPage.shouldSeeUpComing(), "0 Upcoming");
        Assert.assertEquals(myBookingsPage.shouldSeeDeparted(), "0 Departed");
        Assert.assertEquals(myBookingsPage.shouldSeeCancelled(), "0 Cancelled");
    }

    @Test(description = "Verify Default MyReview Page")
    public void VerifyDefaultMyReviewPage() {
        myBookingsPage.clickMyReviewsMenu();
        Assert.assertEquals(myBookingsPage.shouldSeeTextMessageMyReviews(), "You currently have no hotels to review.\n" +
                "Please visit us again after your departure date.");
    }

    @Test(description = "Verify Default MyProfile Page")
    public void VerifyDefaultMyProfilePage() {
        myBookingsPage.clickMyProfileMenu();
        Assert.assertEquals(myBookingsPage.shouldSeeNameInMyProfile(), "Test Agoda");
        Assert.assertEquals(myBookingsPage.shouldSeeEmailInMyProfile(), "test.secude@gmail.com");
    }

    @Test(description = "Verify button Save displayed")
    public void verifyButtonSaveDisplay() {
        myBookingsPage = myBookingsPage.clickMyCardDetails();
        Assert.assertTrue(myBookingsPage.shouldDisplayButtonSave());
    }

    @Test(description = "Verify checkbox is present and default deactivate")
    public void verifyCheckboxDefault() {
        myBookingsPage = myBookingsPage.clickMyCardDetails();
        Assert.assertTrue(myBookingsPage.shouldDisplayCheckbox());
        Assert.assertTrue(myBookingsPage.defaultValueOfCheckboxShouldUncheck());
    }

}
