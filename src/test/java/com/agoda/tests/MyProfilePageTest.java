package com.agoda.tests;

import com.page.objects.LogInPage;
import com.page.objects.MyBookingsPage;
import com.page.objects.MyProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyProfilePageTest extends BasePage {

    private LogInPage logInPage;
    private MyProfilePage myProfilePage;
    private MyBookingsPage myBookingsPage;

    private String EMAIL1;
    private String EMAIL4;
    private String PASSWORD;
    private String GENDER;
    private String COUNTRY;
    private String LANGUAGE;
    private String PHONE;
    private String COLOR;
    private String BACKGROUNDCOLOR;
    private String DATE;
    private String SUCCESS_NOTIFY;
    private String EMAIL;

    @BeforeClass
    public void beforeClass() {
        EMAIL1 = testData.getProperty("email1");
        EMAIL4 = testData.getProperty("email4");
        PASSWORD = testData.getProperty("password3");
        GENDER = testData.getProperty("gender");
        COUNTRY = testData.getProperty("country");
        LANGUAGE = testData.getProperty("language");
        PHONE = testData.getProperty("phone");
        COLOR = testData.getProperty("color");
        BACKGROUNDCOLOR = testData.getProperty("backgroundcolor");
        DATE = testData.getProperty("date");
        SUCCESS_NOTIFY = testData.getProperty("notify");
        EMAIL = testData.getProperty("email");

        logInPage = webPageFactory.loadLogInPage();
        myBookingsPage = logInPage.logInPassed(EMAIL4, PASSWORD);
        myBookingsPage.isLoaded();
        myProfilePage = webPageFactory.getMyProfilePage();
    }

    @BeforeMethod
    public void beforeMethod() {
        myProfilePage.load();
    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     * //
     */
    @Test(description = "verify Default Page Displayed")
    public void VerifyDefaultPageDisplayed() {
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(0), "Basic Information");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(1), "Subscriptions");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(2), "Favorite destinations");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(3), "Mailing Address");
        Assert.assertEquals(myProfilePage.verifyFormTextHeader(4), "My Social Networks");
    }

    @Test(description = "verify Update Information Profile Incorrect")
    public void verifyUpdateInformationProfileIncorrect() {
        myProfilePage.clickMyProfile()
                .clickLinkEditInformation()
                .chooseCountryOfPassport("Please Select")
                .clickLinkSaveInformation();

        Assert.assertTrue(myProfilePage.shouldSeeTextNotificationError("Basic Information is incorrect."));
    }

    @Test(description = "verify Update Information Successfully")
    public void verifyUpdateInformationSuccessfully() {
        myProfilePage.clickMyProfile()
                .clickLinkEditInformation()
                .editEmail("test_selenium@gmail.com")
//                   .chooseCountryOfPassport("Vietnam")
                .selectTheCheckbox()
                .clickLinkSaveInformation();

        //verify information has been changed
//        Assert.assertEquals(myProfilePage.shouldSeeTextCountry(), "Vietnam");
        Assert.assertEquals(myProfilePage.shouldSeeTextBasicInformationUpdateSuccessfully(), "Your Basic Information has been changed successfully.");
//        Assert.assertEquals(myProfilePage.shouldSeeTextEmailEdit(), "test_selenium@gmail.com");
        Assert.assertTrue(myProfilePage.shouldSeeTextCheckboxSelected("Business travelers, Couples, Solo travelers"));

//      //Clean up data
        myProfilePage.clickMyProfile()
                .clickLinkEditInformation()
                .editEmail("testt@gmail.com")
//                   .chooseCountryOfPassport("United Kingdom")
                .deSelectTheCheckbox()
                .clickLinkSaveInformation();
    }
    @Test(description = "verify Edit Information Successfully")
    public void verifyEditInformationSuccessfully() {
            myProfilePage.shouldClickMyProfile()
                .shouldClickLinkEditInformation()
                .shouldChooseGenderName(GENDER)
                .editEmail(EMAIL4)
                .shouldChooseDatePicker("24")
                .shouldChooseCountryPassport(COUNTRY)
                .shouldEditLanguage(LANGUAGE)
                .deSelectTheCheckbox()
                .shouldSelectTheCheckbox()
                .shouldEnterMobilePhoneNumber(PHONE)
                .clickSaveAllInformation();
        Assert.assertEquals(myProfilePage.shouldSeeSuccessNotify(), SUCCESS_NOTIFY);
        Assert.assertEquals(myProfilePage.shouldSeeTextColor(), COLOR);
        Assert.assertEquals(myProfilePage.shouldSeeBackgroundColor(), BACKGROUNDCOLOR);
        Assert.assertEquals(myProfilePage.shouldSeeEditedEmail(), EMAIL);
        Assert.assertEquals(myProfilePage.shouldSeeEditedLanguage(), LANGUAGE);
        Assert.assertEquals(myProfilePage.shouldSeeCountryPassport(),COUNTRY );
    }
}
