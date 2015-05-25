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

    private String EMAIL_VALID;
    private String EMAIL_NOT_EXIST;
    private String EMAIL_INVALID;
    private String EMAIL_LOCKED;
    private String PASSWORD_VALID;
    private String MSG_ERROR_WRONG_EMAIL_PASSWORD;
    private String MSG_ERROR_EMAIL_LOCKED;
    private String MSG_VALIDATION_EMAIL_BLANK;
    private String MSG_VALIDATION_EMAIL_INVALID;
    private String MSG_VALIDATION_PASSWORD_BLANK;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        logInPage = webPageFactory.loadLogInPage();

        EMAIL_VALID = testData.getProperty("email.valid");
        EMAIL_NOT_EXIST = testData.getProperty("email.not.exist");
        EMAIL_INVALID = testData.getProperty("email.invalid");
        EMAIL_LOCKED = testData.getProperty("email.locked");
        PASSWORD_VALID = testData.getProperty("password.valid");
        MSG_ERROR_WRONG_EMAIL_PASSWORD = testData.getProperty("msg.error.wrong.email.password");
        MSG_ERROR_EMAIL_LOCKED = testData.getProperty("msg.error.email.locked");
        MSG_VALIDATION_EMAIL_BLANK = testData.getProperty("msg.validation.email.blank");
        MSG_VALIDATION_EMAIL_INVALID = testData.getProperty("msg.validation.email.invalid");
        MSG_VALIDATION_PASSWORD_BLANK = testData.getProperty("msg.validation.password.blank");
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
    @Test(description = "Login-Success-ValidEmailPassword")
    public void verifyLogInPassed() {
        myBookingsPage = logInPage.enterEmail(EMAIL_VALID)
                                  .enterPassword(PASSWORD_VALID)
                                  .clickSignInPassed();

        // Verify the displayed correct page
        myBookingsPage.isLoaded();

        // User signs out
        logInPage = myBookingsPage.clickSignOut();
        logInPage.isLoaded();
    }

    @Test(description = "Login-Failure-EmailOrPasswordIncorrect")
    public void verifyLogInFailedWithIncorrectEmailOrPassword() {
        logInPage.enterEmail(EMAIL_NOT_EXIST)
                 .enterPassword(PASSWORD_VALID)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailPasswordError(MSG_ERROR_WRONG_EMAIL_PASSWORD));
    }

    @Test(description = "Login-Validation-InvalidEmail")
    public void verifyValidationWithInvalidEmail() {
        logInPage.enterEmail(EMAIL_INVALID)
                .enterPassword(PASSWORD_VALID)
                .clickSignInFailed();

        // Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailError(MSG_VALIDATION_EMAIL_INVALID));
    }

    @Test(description = "Login-Validation-BlankEmail")
    public void verifyValidationWithBlankEmail() {
        logInPage.enterPassword(PASSWORD_VALID)
                 .clickSignInFailed();

        // Verify the validation message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(MSG_VALIDATION_EMAIL_BLANK));
    }

    @Test(description = "Login-Validation-BlankPassword")
    public void verifyValidationWithBlankPassword() {
        logInPage.enterEmail(EMAIL_VALID)
                 .clickSignInFailed();

        // Verify the validation message displayed
        Assert.assertTrue(logInPage.shouldSeeTextPasswordError(MSG_VALIDATION_PASSWORD_BLANK));
    }

    @Test(description = "Login-Validation-BlankEmail&Password")
    public void verifyValidationWithBlankEmailAndPassword() {
        logInPage.clickSignInFailed();

        //Verify the displayed message
        Assert.assertTrue(logInPage.shouldSeeTextEmailValidation(MSG_VALIDATION_EMAIL_BLANK));
        Assert.assertTrue(logInPage.shouldSeeTextPasswordError(MSG_VALIDATION_PASSWORD_BLANK));
    }

    @Test(description = "Verify logging in failed")
    public void verifyLogInFailedWithLockedEmail() {
        logInPage.enterEmail(EMAIL_LOCKED)
                 .enterPassword(PASSWORD_VALID)
                 .clickSignInFailed();

        // Verify the error message displayed
        Assert.assertTrue(logInPage.shouldSeeTextEmailPasswordError(MSG_ERROR_EMAIL_LOCKED));
    }

}
