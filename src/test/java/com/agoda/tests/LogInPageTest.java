package com.agoda.tests;

import com.page.objects.MyBookingsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.page.objects.LogInPage;

public class LogInPageTest extends BasePage {

    private LogInPage logInPage;

    private String EMAIL1;
    private String EMAIL2;
    private String EMAIL3;
    private String PASSWORD;
    private String PASSWORD2;
    private String ERROR_MSG_WRONG_EMAIL_PASS;
    private String ERROR_MSG_WRONG_EMAIL;
    private String ERROR_MSG_WRONG_PASS;
    private String ERROR_MSG_REQUIRED_EMAIL;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        logInPage = webPageFactory.loadLogInPage();

        EMAIL1 = testData.getProperty("email1");
        EMAIL2 = testData.getProperty("email2");
        EMAIL3 = testData.getProperty("email3");
        PASSWORD = testData.getProperty("password");
        PASSWORD2 = testData.getProperty("password2");
        ERROR_MSG_WRONG_EMAIL_PASS = testData.getProperty("errorMsg.WrongEmailOrPass");
        ERROR_MSG_WRONG_EMAIL = testData.getProperty("errorMsg.WrongEmail");
        ERROR_MSG_WRONG_PASS = testData.getProperty("errorMsg.WrongPass");
        ERROR_MSG_REQUIRED_EMAIL = testData.getProperty("errorMsg.RequiredEmail");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        refreshPage();
    }


    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "Verify logging in passed", groups = {"smoke"})
    public void verifyLogInPassed() {
        MyBookingsPage myBookingsPage = logInPage.enterEmail(EMAIL1)
                .enterPassword(PASSWORD)
                .clickSignInPassed();

        // Verify the displayed correct page
        myBookingsPage.isLoaded();

        // User signs out
        logInPage = myBookingsPage.clickSignOut();
        logInPage.isLoaded();
    }

    @Test(description = "Verify logging in failed", groups = {"smoke"})
    public void verifyLogInFailed() {
        logInPage.enterEmail(EMAIL2)
                .enterPassword(PASSWORD)
                .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertEquals(logInPage.signInNotificationText(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

    @Test(description = "Login-Failure-BlankEmail")
    public void verifyLogInFailedBlankEmail() {
        logInPage.enterPassword(PASSWORD)
                .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.seeRedErrorMsgEmailRequired("Email Address is required."));
    }

    // Thach
    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithInvalidEmail() {
        logInPage.enterEmail(EMAIL3)
                .enterPassword(PASSWORD)
                .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationTextEmail(), ERROR_MSG_WRONG_EMAIL);
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithBlankPassword() {
        logInPage.enterEmail(EMAIL1)
                .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationTextPassword(), ERROR_MSG_WRONG_PASS);
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithInvalidPassword() {
        logInPage.enterEmail(EMAIL1)
                .enterPassword(PASSWORD2)
                .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationText(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithoutEmailPassword() {
        logInPage.clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationTextRequiredEmail(), ERROR_MSG_REQUIRED_EMAIL);
        Assert.assertEquals(logInPage.signInNotificationTextPassword(), ERROR_MSG_WRONG_PASS);
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithEmailBlock() {
        logInPage.enterEmail(EMAIL2)
                .enterPassword(PASSWORD)
                .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationText(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

}
