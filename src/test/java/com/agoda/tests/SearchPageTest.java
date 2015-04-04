package com.agoda.tests;

import com.page.objects.SearchPage;
import com.page.objects.SearchResultPage;
import com.web.coreframework.Common;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends BasePage {

    private SearchPage searchPage;
    private String KEYWORD;
    private String COLOR_BLUE;
    private String SEARCH_QUERY_1;

    @BeforeClass
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();

        SEARCH_QUERY_1 = testData.getProperty("searchQuery1");
        COLOR_BLUE = testData.getProperty("color_blue");
        KEYWORD = testData.getProperty("keyword");
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
//    @Test(description = "SearchPanel-VerifyAutoCompleteWhenTyping")
//    public void verifySearchShowAutoComplete() {
//        searchPage.inputText(SEARCH_QUERY_1);
//        Assert.assertTrue(searchPage.isAutoCompleteResultDisplayed());
//    }
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

    @Test(description = "Verify search success")
    public void verifySearchingShowResult() {
        SearchResultPage searchResultPage = searchPage.inputText(KEYWORD)
                .clickSearchButton();
        searchResultPage.isLoad();

        Assert.assertEquals(searchResultPage.verifySearchText(), "Your search for ho chi minh matched the following...");
        Assert.assertTrue(searchResultPage.isSearchTextBold());
        Assert.assertEquals(searchResultPage.searchTextColor(), COLOR_BLUE);
        Assert.assertEquals(searchResultPage.countMenuItems(), 6);
        Assert.assertEquals(searchResultPage.menuItems(0), "All");
        Assert.assertEquals(searchResultPage.menuItems(1), "Cities");
        Assert.assertEquals(searchResultPage.menuItems(2), "Areas");
        Assert.assertEquals(searchResultPage.menuItems(3), "Airports");
        Assert.assertEquals(searchResultPage.menuItems(4), "Transport");
        Assert.assertEquals(searchResultPage.menuItems(5), "Landmarks");
        Assert.assertEquals(searchResultPage.isCountListHotels(), 99);
    }

    @Test(description = "verify search show result")
    public void verifySearchingShowAutoComplete() {
        searchPage.load();
        searchPage.inputText("lond");
        Common.sleep(5000);
        Assert.assertTrue(searchPage.isShowPanelAutoCompleteSearch());
        Assert.assertEquals(searchPage.isCountPanelMenuItems(), 6);
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