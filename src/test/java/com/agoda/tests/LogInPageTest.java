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
    private String PASSWORD;
    private String ERROR_MSG_WRONG_EMAIL_PASS;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        logInPage = webPageFactory.loadLogInPage();

        EMAIL1 = testData.getProperty("email1");
        EMAIL2 = testData.getProperty("email2");
        PASSWORD = testData.getProperty("password");
        ERROR_MSG_WRONG_EMAIL_PASS = testData.getProperty("errorMsg.WrongEmailOrPass");
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

        // Verify the displayed message
        Assert.assertEquals(logInPage.signInNotificationText(), ERROR_MSG_WRONG_EMAIL_PASS);
    }

}
