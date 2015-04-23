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
    private final String COLOR_BLUE = "#0283df";

    @BeforeClass
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();

        SEARCH_QUERY_1 = testData.getProperty("searchQuery1");
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
        String query = "bong da tivi";
        searchResultsPage = searchPage.enterSearchQuery(query)
                                      .chooseDay("Thu 12")
                                      .chooseMonthYear("Feb, 2015")
                                      .chooseDataPicker()
                                      .chooseNight("3")
                                      .chooseDatePickerCheckOut()
                                      .clickSearchButtonShowResultsPage();

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your Search for %s had 0 results.", query));
    }

    @Test(description = "Search-EmptyValueInput")
    public void verifySearchWithEmptyValueSearchInput() {
        searchPage.enterSearchQuery("")
                  .clickSearchButtonShowValidation();

        Assert.assertEquals(searchPage.shouldSeeTextAlert(), "Please enter the name of a Country, City, Airport, Area, Landmark or Hotel to proceed.");
    }

    @Test(description = "Search-NumberSpecialCharacter")
    public void verifySearchWithSpecialCharacters() {
        String query = "123@#$hanoi";
        String queryAfterSearch = "123hanoi";
        searchResultsPage = searchPage.enterSearchQuery(query)
                                      .chooseDay("Thu 12")
                                      .chooseMonthYear("Feb, 2015")
                                      .chooseDataPicker()
                                      .chooseNight("3")
                                      .chooseDatePickerCheckOut()
                                      .clickSearchButtonShowResultsPage();

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your Search for %s had 0 results.", queryAfterSearch));
    }

    @Test(description = "Search-ResultsFound")
    public void verifySearchShowResultsFound() {
        String query = "ho chi minh";
        searchResultsPage = searchPage.searchWithQuery(query);

        Assert.assertEquals(searchResultsPage.shouldSeeTextSearchResultMessage(), String.format("Your search for %s matched the following...", query));
        Assert.assertEquals(searchResultsPage.shouldSeeColorSearchQuery(), COLOR_BLUE);
    }

}
