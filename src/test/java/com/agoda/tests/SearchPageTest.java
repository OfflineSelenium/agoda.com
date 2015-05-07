package com.agoda.tests;

import com.page.objects.SearchPage;
import com.page.objects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends BasePage {

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    private String SEARCH_QUERY_1;
    private String SEARCH_QUERY_2;
    private String SEARCH_QUERY_3;
    private String AFTER_SEARCH_QUERY_3;
    private String SEARCH_QUERY_4;
    private final String COLOR_BLUE = "#0283df";

    @BeforeClass
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();

        SEARCH_QUERY_1 = testData.getProperty("searchQuery1");
        SEARCH_QUERY_2 = testData.getProperty("searchQuery2");
        SEARCH_QUERY_3 = testData.getProperty("searchQuery3");
        AFTER_SEARCH_QUERY_3 = testData.getProperty("queryAfterSearch");
        SEARCH_QUERY_4 = testData.getProperty("searchQuery4");
    }

    @BeforeMethod
    public void beforeMethod() {
        searchPage.load();
    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "SearchPanel-VerifyDefaultUI")
    public void verifySearchWithDefaultUI() {
        Assert.assertEquals(searchPage.shouldSeeTextSearchInput(), "");
        Assert.assertEquals(searchPage.shouldSeeTextSelectedNightCountDropdown(), "2");
        Assert.assertEquals(searchPage.shouldSeeTextSelectedGuestsDropdown(), "2 adults (1 room)");
        Assert.assertTrue(searchPage.shouldDisplaySearchButton());
        Assert.assertTrue(searchPage.shouldDisplayCheckInDatePicker());
        Assert.assertTrue(searchPage.shouldDisplayCheckOutDatePicker());
    }

    @Test(description = "SearchPanel-VerifyAutoCompleteWhenTyping")
    public void verifySearchShowAutoComplete() {
        searchPage.enterSearchQuery(SEARCH_QUERY_1);
        Assert.assertTrue(searchPage.shouldDisplayAutoCompleteResult());
    }

    @Test(description = "SearchPanel-VerifyAgodaPriceGuaranteeLink")
    public void verifyAgodaPriceGuaranteeLink() {
        searchPage.clickAgodaPriceGuaranteeLink();

        Assert.assertTrue(searchPage.shouldDisplayAgodaPriceGuaranteePopup());
        Assert.assertTrue(searchPage.shouldDisplayAgodaPriceGuaranteePopupLabel("Agoda price guarantee"));
        Assert.assertTrue(searchPage.shouldDisplayAgodaPriceGuaranteePopupHeading("Best Price Guarantee"));
    }

    @Test(description = "Search-NoResults")
    public void verifySearchWithNoResults() {
        searchResultsPage = searchPage.enterSearchQuery(SEARCH_QUERY_2)
                                      .chooseDay("Thu 12")
                                      .chooseMonthYear("Feb, 2015")
                                      .chooseDataPicker()
                                      .chooseNight("3")
                                      .chooseDatePickerCheckOut()
                                      .clickSearchButtonShowResultsPage();

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your Search for %s had 0 results.", SEARCH_QUERY_2));
    }

    @Test(description = "Search-EmptyValueInput")
    public void verifySearchWithEmptyValueSearchInput() {
        searchPage.enterSearchQuery("")
                  .clickSearchButtonShowValidation();

        Assert.assertEquals(searchPage.shouldSeeTextAlert(), "Please enter the name of a Country, City, Airport, Area, Landmark or Hotel to proceed.");
    }

    @Test(description = "Search-NumberSpecialCharacter")
    public void verifySearchWithSpecialCharacters() {
        searchResultsPage = searchPage.enterSearchQuery(SEARCH_QUERY_3)
                                      .chooseDay("Thu 12")
                                      .chooseMonthYear("Feb, 2015")
                                      .chooseDataPicker()
                                      .chooseNight("3")
                                      .chooseDatePickerCheckOut()
                                      .clickSearchButtonShowResultsPage();

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your Search for %s had 0 results.", AFTER_SEARCH_QUERY_3));
    }

    @Test(description = "Search-ResultsFound")
    public void verifySearchShowResultsFound() {
        searchResultsPage = searchPage.searchWithQuery(SEARCH_QUERY_4);

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your search for %s matched the following...", SEARCH_QUERY_4));
        Assert.assertEquals(searchResultsPage.shouldSeeColorSearchQuery(), COLOR_BLUE);
    }

}
