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

    private String VALID_EMAIL;
    private String EMAIL_BLOCKED;
    private String EMAIL_INVALID;
    private String VALID_PASSWORD;
    private String INVALID_PASSWORD;
    private String WRONG_EMAIL_OR_PASS;
    private String WRONG_EMAIL;
    private String WRONG_PASS;
    private String REQUIRED_EMAIL;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        logInPage = webPageFactory.loadLogInPage();

        VALID_EMAIL = testData.getProperty("validEmail");
        EMAIL_BLOCKED = testData.getProperty("emailBlocked");
        EMAIL_INVALID = testData.getProperty("invalidEmail");
        VALID_PASSWORD = testData.getProperty("validPassword");
        INVALID_PASSWORD = testData.getProperty("invalidPassword");
        WRONG_EMAIL_OR_PASS = testData.getProperty("errorMsg.WrongEmailOrPass");
        WRONG_EMAIL = testData.getProperty("errorMsg.WrongEmail");
        WRONG_PASS = testData.getProperty("errorMsg.WrongPass");
        REQUIRED_EMAIL = testData.getProperty("errorMsg.RequiredEmail");
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
        myBookingsPage = logInPage.enterEmail(VALID_EMAIL)
                                  .enterPassword(VALID_PASSWORD)
                                  .clickSignInPassed();

        // Verify the displayed correct page
        myBookingsPage.isLoaded();

        // User signs out
        logInPage = myBookingsPage.clickSignOut();
        logInPage.isLoaded();
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailed() {
        logInPage.enterEmail(VALID_EMAIL)
                 .enterPassword(INVALID_PASSWORD)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailPasswordError(WRONG_EMAIL_OR_PASS));
    }

    @Test(description = "Login-Failure-BlankEmail")
    public void verifyLogInFailedBlankEmail() {
        logInPage.enterPassword(VALID_PASSWORD)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(REQUIRED_EMAIL));
    }

    @Test(description = "Login-Failure-InvalidEmail")
    public void verifyLogInFailedWithInvalidEmail() {
        logInPage.enterEmail(EMAIL_INVALID)
                 .enterPassword(VALID_PASSWORD)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailError(WRONG_EMAIL));
    }

    @Test(description = "Login-Failure-BlankPassword")
    public void verifyLogInFailedWithBlankPassword() {
        logInPage.enterEmail(VALID_EMAIL)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextPasswordError(WRONG_PASS));
    }

    @Test(description = "Login-Failure-InvalidPassword")
    public void verifyLogInFailedWithInvalidPassword() {
        logInPage.enterEmail(VALID_EMAIL)
                 .enterPassword(INVALID_PASSWORD)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailPasswordError(WRONG_EMAIL_OR_PASS));
    }

    @Test(description = "Login-Failure-WithoutEmailPassword")
    public void verifyLogInFailedWithoutEmailPassword() {
        logInPage.clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(REQUIRED_EMAIL));
        Assert.assertTrue(logInPage.shouldSeeTextPasswordError(WRONG_PASS));
    }

    @Test(description = "Login-Failure-WithoutEmailBlock")
    public void verifyLogInFailedWithEmailBlock() {
        logInPage.enterEmail(EMAIL_BLOCKED)
                 .enterPassword(VALID_PASSWORD)
                 .clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailPasswordError(WRONG_EMAIL_OR_PASS));
    }
}
