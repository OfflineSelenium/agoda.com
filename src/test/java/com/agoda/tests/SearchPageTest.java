package com.agoda.tests;

import com.page.objects.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends BasePage {

    private SearchPage searchPage;

    @BeforeClass
    public void beforeClass(){
        searchPage=webPageFactory.loadSearchPage();
    }

    @BeforeMethod
    public void beforeMethod(){
        refreshPage();
    }

    @Test
    public void checkAutocompleteWhenInput(){
        searchPage=searchPage.inputText("phong");
        Assert.assertTrue(searchPage.selectFirstItemAutocompleteDisplay());

    }
}
