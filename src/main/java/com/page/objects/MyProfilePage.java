package com.page.objects;

import com.web.coreframework.Common;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyProfilePage extends GeneralPage {

    @FindBy(id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyProfile")
    private WebElement linkMyProfile;

    @FindBy(id = "lbtEditBasicInfo")
    private WebElement linkEditInformation;

    @FindBy(id = "ddlCountryOfPassport")//xpath = "//select[@id='ddlCountryOfPassport']/option[@selected='selected']")
    private WebElement ChooseCountry;

    @FindBy(id = "lbtSaveBasicInfo")
    private WebElement linkSaveInformation;

    @FindBy(xpath = "//p[@class='h_textprofile']")
    private List<WebElement> formTextHeader;

    @FindBy(id = "txtEmail")
    private WebElement inputEmail;

    @FindBy(id = "ddlLanguage")
    private WebElement language;

    @FindBy(id = "txtPhone")
    private WebElement phone;

    @FindBy(className = "ui-datepicker-trigger")
    private WebElement datepicker;

    @FindBy(id = "ddlTitle")
    private WebElement gendername;

    public void load() {
        loadPage("/rewards/profile.html");
    }

    public void isload() {
        webPageFactory.checkWeAreOnTheRightPage("My Profile");
    }

    public MyProfilePage clickMyProfile() {
        linkMyProfile.click();
        Common.sleep(5000);
        return this;
    }

    public MyProfilePage clickLinkEditInformation() {
        linkEditInformation.click();
        Common.sleep(5000);
        return this;
    }

    public MyProfilePage chooseCountryOfPassport(String country) {
        ChooseCountry.sendKeys(country);
        return this;
    }

    public MyProfilePage clickLinkSaveInformation() {
        linkSaveInformation.click();
        Common.sleep(5000);
        return this;
    }

    public Boolean shouldSeeTextNotificationError(String text) {
        String xpath = "//*[@id='basicSummaryError']/ul/li[1]";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public String shouldSeeTextCountry() {
        String xpath = "//p[@class='info_right'][4]";
        return webElementFinder.findElementByLocatorXPath(xpath).getText();
    }

    public String shouldSeeTextBasicInformationUpdateSuccessfully() {
        return webElementFinder.findElementByLocatorXPath("//span[contains(@class,'text_green')]").getText();
    }

    public String verifyFormTextHeader(int i) {
        return formTextHeader.get(i).getText();
    }

    public MyProfilePage editEmail(String e_email) {
        inputEmail.clear();
        inputEmail.sendKeys(e_email);
        return this;
    }

//    public String shouldSeeTextEmailEdit() {
//        String xpath = "//p[@class='info_right'][2]";
//        return webElementFinder.findElementByLocatorXPath(xpath).getText();
//    }

    public MyProfilePage selectTheCheckbox() {
        String xpath = "//table[@id='cblTravelerTypeList']//input[@type='checkbox']";
        List<WebElement> cks = webElementFinder.findElementsByLocatorXPath(xpath);
        for (WebElement option : cks) {
            option.click();
        }
        return this;
    }

    public MyProfilePage deSelectTheCheckbox() {
        String xpath = "//table[@id='cblTravelerTypeList']//input[@type='checkbox']";
        List<WebElement> cks = webElementFinder.findElementsByLocatorXPath(xpath);
        for (WebElement option : cks) {
            if (option.isSelected()) {
                option.click();
            }
        }
        return this;
    }

//    public boolean verifyMaxCheckboxSelected() {
//        String xpath = "//table[@id='cblTravelerTypeList']//input[@checked='checked']";
//        List<WebElement> ck = webElementFinder.findElementsByLocatorXPath(xpath);
//        int count = 0;
//        for (WebElement elm : ck) {
//            count++;
//        }
//        if (count < 2)
//            return true;
//        else
//            return false;
//    }

    public Boolean shouldSeeTextCheckboxSelected(String text) {
        String xpath = "//div[@class='info_right']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        return element.getText().equals(text);
    }

    public MyProfilePage shouldChooseGenderName(String gender) {
        String id = "ddlTitle";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.sendKeys(gender);
        return this;
    }

    public MyProfilePage shouldChooseDatePicker(String date) {
        datepicker.click();
        String xpath = "//a[@class ='ui-state-default']";
        WebElement element = webElementFinder.findElementByLocatorXPath(xpath);
        element.sendKeys(date);
        return this;
    }

    public MyProfilePage shouldChooseLanguage(String lang) {
        language.sendKeys(lang);
        return this;
    }

    public MyProfilePage shouldEnterMobilePhoneNumber(String phonenumber) {
        String id = "txtPhone";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.clear();
        element.sendKeys(phonenumber);
        return this;
    }

    public MyProfilePage shouldSelectTheCheckbox() {

        String xpath = "//table[@id='cblTravelerTypeList']//input[@type='checkbox']";
        List<WebElement> cks = webElementFinder.findElementsByLocatorXPath(xpath);
        int i = 0;
        if (i <= cks.size() - 3)
            for (WebElement element : cks) {
                if (element.isSelected())
                    element.click();
            }
        i++;
        return this;
    }

    public MyProfilePage shouldChooseCountryPassport(String country) {
        String id = "ddlCountryOfPassport";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.sendKeys(country);
        return this;
    }

    public MyProfilePage shouldEditLanguage(String language) {
        String id = "ddlLanguage";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.sendKeys(language);
        return this;
    }

    public MyProfilePage clickSaveAllInformation() {
        String id = "lbtSaveBasicInfo";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.click();
        return this;
    }

    public MyProfilePage shouldClickLinkEditInformation() {
        String id = "lbtEditBasicInfo";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.click();
        return this;
    }

    public String shouldSeeEditedName() {
        String xpath = "//p[@class ='info_right'][1]";
        List<WebElement> elements = webElementFinder.findElementsByLocatorXPath(xpath);
        return elements.get(0).getText();
    }

    public String shouldSeeEditedEmail() {
        String xpath = "//p[@class='info_right'][2]";
        List<WebElement> elm = webElementFinder.findElementsByLocatorXPath(xpath);
        return elm.get(0).getText();
    }

    public String shouldSeeEditedLanguage() {
        String xpath = "//p[@class='info_right'][5]";
        List<WebElement> elements = webElementFinder.findElementsByLocatorXPath(xpath);
        return elements.get(0).getText();
    }

    public String shouldSeeCountryPassport() {
        String xpath = "//p[@class='info_right'][4]";
        List<WebElement> elements = webElementFinder.findElementsByLocatorXPath(xpath);
        return elements.get(0).getText();
    }

    public MyProfilePage shouldClickMyProfile() {
        String id = "ctl00_ctl00_MainContent_leftMenu1_lbtMyProfile";
        WebElement element = webElementFinder.findElementByLocatorID(id);
        element.click();
        return this;
    }

    public String shouldSeeSuccessNotify() {
        String classname = "text_green";
        WebElement elements = webElementFinder.findElementByLocatorClassName(classname);
        return elements.getText();
    }

    public String shouldSeeTextColor() {
        String classname = "text_green";
        WebElement element = webElementFinder.findElementByLocatorClassName(classname);
        String color = element.getCssValue("color");
        return Common.convertToHex(color);
    }

    public String shouldSeeBackgroundColor() {
        String classname = "success_info";
        WebElement element = webElementFinder.findElementByLocatorClassName(classname);
        String color = element.getCssValue("background-color");
        return Common.convertToHex(color);
    }

}


