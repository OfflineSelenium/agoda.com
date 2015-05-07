package com.agoda.tests;

import com.page.objects.MyBookingsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.page.objects.LogInPage;

public class LogInPageTest extends BasePage {

    private LogInPage logInPage;
    private MyBookingsPage myBookingsPage;

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
    @Test(description = "Verify logging in passed")
    public void verifyLogInPassed() {
        myBookingsPage = logInPage.enterEmail(EMAIL1)
                                  .enterPassword(PASSWORD)
                                  .clickSignInPassed();

        // Verify the displayed correct page
        myBookingsPage.isLoaded();

        // User signs out
        logInPage = myBookingsPage.clickSignOut();
        logInPage.isLoaded();
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailed() {
        logInPage.enterEmail(EMAIL2)
                 .enterPassword(PASSWORD)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertEquals(logInPage.shouldSeeTextEmailPasswordError(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

    @Test(description = "Login-Failure-BlankEmail")
    public void verifyLogInFailedBlankEmail() {
        logInPage.enterPassword(PASSWORD)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(ERROR_MSG_REQUIRED_EMAIL));
    }

    @Test(description = "Login-Failure-InvalidEmail")
    public void verifyLogInFailedWithInvalidEmail() {
        logInPage.enterEmail(EMAIL3)
                 .enterPassword(PASSWORD)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.shouldSeeTextEmailError(), ERROR_MSG_WRONG_EMAIL);
    }

    @Test(description = "Login-Failure-BlankPassword")
    public void verifyLogInFailedWithBlankPassword() {
        logInPage.enterEmail(EMAIL1)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.shouldSeeTextPasswordError(), ERROR_MSG_WRONG_PASS);
    }

    @Test(description = "Login-Failure-InvalidPassword")
    public void verifyLogInFailedWithInvalidPassword() {
        logInPage.enterEmail(EMAIL1)
                 .enterPassword(PASSWORD2)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.shouldSeeTextEmailPasswordError(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

    @Test(description = "Login-Failure-WithoutEmailPassword")
    public void verifyLogInFailedWithoutEmailPassword() {
        logInPage.clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(ERROR_MSG_REQUIRED_EMAIL));
        Assert.assertEquals(logInPage.shouldSeeTextPasswordError(), ERROR_MSG_WRONG_PASS);
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithEmailBlock() {
        logInPage.enterEmail(EMAIL2)
                 .enterPassword(PASSWORD)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertEquals(logInPage.shouldSeeTextEmailPasswordError(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

}
