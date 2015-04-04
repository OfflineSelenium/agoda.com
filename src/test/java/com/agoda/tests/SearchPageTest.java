package com.agoda.tests;

import com.page.objects.SearchPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends BasePage {

    private SearchPage searchPage;

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
}