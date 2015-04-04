package com.agoda.tests;

import com.page.objects.SearchPage;
import com.page.objects.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class SearchPageTest extends BasePage {

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;
    private WebDriver driver;

    private String SEARCH_QUERY_1;

    @BeforeClass
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();

        SEARCH_QUERY_1 = testData.getProperty("searchQuery1");
    }

    @BeforeMethod
    public void beforeMethod() {
        refreshPage();
    }


    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
//    @Test //BinhLe
//    public void checkAutocompleteWhenInput() {
//        searchPage = searchPage.inputText("phong");
//        Assert.assertTrue(searchPage.selectFirstItemAutocompleteDisplay());
//    }
    @Test(description = "SearchPanel-VerifyAutoCompleteWhenTyping")
    public void verifySearchShowAutoComplete() {
        searchPage.inputText(SEARCH_QUERY_1);
        Assert.assertTrue(searchPage.isAutoCompleteResultDisplayed());
    }

    @Test(description = "SearchPanel-VerifyDefaultUI")
    public void verifySearchWithDefaultUI() {
        Assert.assertEquals(searchPage.valueSearchInput(), "");
        Assert.assertEquals(searchPage.valueSelectedNightCountDropdown(), "2");
        Assert.assertEquals(searchPage.valueSelectedGuestsDropdown(), "2 adults (1 room)");
        Assert.assertTrue(searchPage.getSearchButton().isDisplayed());
        Assert.assertTrue(searchPage.getCheckInDatePicker().isDisplayed());
        Assert.assertTrue(searchPage.getCheckOutDatePicker().isDisplayed());
    }

    @Test(description = "SearchPanel-VerifyAgodaPriceGuaranteeLink")
    public void verifyAgodaPriceGuaranteeLink() {
        searchPage.clickAgodaPriceGuaranteeLink();

        // Verify the popup displayed
        Assert.assertTrue(searchPage.seeAgodaPriceGuaranteePopup());
        Assert.assertTrue(searchPage.seeAgodaPriceGuaranteePopupLabel("Agoda price guarantee"));
        Assert.assertTrue(searchPage.seeAgodaPriceGuaranteePopupHeading("Best Price Guarantee"));
    }

//    @Test
//    public void checkBottomLink() {
//        Assert.assertEquals(searchPage.getBottomTextLink(), "Agoda price guarantee");
//    }
//
//    @Test
//    public void checkZoomOut() {
//        searchPage.zoomOut(6);
//    }
//
//    @Test
//    public void checkZoomIn() {
//        searchPage.zoomIn(3);
//    }
//
//    @Test
//    public void checkZoomReset() {
//        searchPage.zoomReset();
//    }
//
//    @Test
//    public void checkResizeWindowSmaller() {
//        searchPage.resizeWindowToSmaller();
//    }
//
//    @Test
//    public void checkResizeWindowLarger() {
//        searchPage.resizeWindowToLarger();
//    }
//
//    @Test
//    public void checkResizeWindowReset() {
//        searchPage.resizeWindowReset();
//    }

    @Test(description = "searchWrongData")
    public void searchFail() {
        searchPage.load();
        searchPage.inputText("bong da tivi")
                  .chooseDay("Thu 12")
                  .chooseMonthYear("Feb, 2015")
                  .chooseDataPicker()
                  .chooseNight("3")
                  .chooseDatePickerCheckOut();
        searchResultsPage = searchPage.clickSearchButton();
    }

    @Test(description = "searchNonPrintingKey")
    public void searchNonPrintingKey() {
        searchPage.load();
        searchPage.inputKey()
                  .chooseDay("Thu 12")
                  .chooseMonthYear("Feb, 2015")
                  .chooseDataPicker()
                  .chooseNight("3")
                  .chooseDatePickerCheckOut();
        searchResultsPage = searchPage.clickSearchButton();
    }

    @Test(description = "searchSpecialCharacters")
    public void searchSpecialCharacters() {
        searchPage.load();
        searchPage.inputText("123@#$hanoi")
                  .chooseDay("Thu 12")
                  .chooseMonthYear("Feb, 2015")
                  .chooseDataPicker()
                  .chooseNight("3")
                  .chooseDatePickerCheckOut();
        searchResultsPage = searchPage.clickSearchButton();
    }

    private final String blueColor = "#0283df";

    @Test(description = "searchValid", groups = "smoke")
    public void searchValid() {
        searchPage.load();
        SearchResultsPage searchResultsPage = searchPage.inputText("ho chi minh")
//        Actions builder = new Actions(driver);
//        builder.sendKeys(Keys.ESCAPE);
//        builder.build().perform();
                .chooseDay("Thu 12")
                .chooseMonthYear("Feb, 2015")
                .chooseDataPicker()
                .chooseNight("3")
                .chooseDatePickerCheckOut()
                .clickSearchButton();
        searchResultsPage.isLoad();
//        List<WebElement> list = driver.findElements(By.xpath("//ol[contains(@class,'result result-list text-result content-list isotope')]/li"));
//        int count = 0;
//        for (WebElement element : list) {
//            count++;
//        }
//        System.out.println(count);
        Assert.assertEquals(searchResultsPage.getColor(), blueColor);
    }

    @AfterClass(alwaysRun = true)
    public void aftermethod() {
        webPageFactory.getDriver().close();
    }
}