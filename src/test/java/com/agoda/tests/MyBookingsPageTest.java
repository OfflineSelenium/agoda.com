package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyBookingsPageTest extends BasePage {

    private MyBookingsPage myBookingsPage;
    private LogInPage logInPage;
    private String EMAIL;
    private String PASSWORD;

    @BeforeClass
    public void beforeClass() {
        logInPage = webPageFactory.getLogInPage();
        EMAIL = testData.getProperty("email1");
        PASSWORD = testData.getProperty("password");
    }

    @BeforeMethod
    public void beforeMethod() {
        logInPage.load();
    }

      @Test(description = "VerifyDefaultMyBookingsPage")
      public void VerifyDefaultMyBookingsPage() {
          myBookingsPage = logInPage.enterEmail(EMAIL)
                  .enterPassword(PASSWORD)
                  .clickSignInPassed();
          myBookingsPage.isLoaded();
          Assert.assertEquals(myBookingsPage.getResultSearch(), "0 Total Bookings");
      }

      @Test(description = "VerifyDefaultMyReviewPage")
      public void VerifyDefaultMyReviewPage() {
          myBookingsPage = logInPage.enterEmail(EMAIL)
                  .enterPassword(PASSWORD)
                  .clickSignInPassed();
          myBookingsPage.isLoaded();
          myBookingsPage.clickMyReviewsMenu();
          Assert.assertEquals(myBookingsPage.getTextMyReviews(), "You currently have no hotels to review.\n" +
                  "Please visit us again after your departure date.");
      }

      @Test(description = "VerifyDefaultMyProfilePage")
      public void VerifyDefaultMyProfilePage() {
          myBookingsPage = logInPage.enterEmail(EMAIL)
                  .enterPassword(PASSWORD)
                  .clickSignInPassed();
          myBookingsPage.isLoaded();
          myBookingsPage.clickMyProfileMenu();
          Assert.assertEquals(myBookingsPage.getNameInMyProfile(), "Test Agoda");
          Assert.assertEquals(myBookingsPage.getNameInMyProfile(),"test.secude@gmail.com");
      }

    @Test(description = "VerifyDefaultMyCardPage")
    public void VerifyDefaultMyCardPage() {
        myBookingsPage = logInPage.enterEmail(EMAIL)
                .enterPassword(PASSWORD)
                .clickSignInPassed();
        myBookingsPage.isLoaded();
        myBookingsPage.clickMyCardDetails();
        Assert.assertEquals(myBookingsPage.getPopulateEmailInMyCardDetails(), "test.secude@gmail.com");
    }
}
