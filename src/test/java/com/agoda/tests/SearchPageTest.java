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

    @Test
    public void checkBottomLink(){
        Assert.assertEquals(searchPage.getBottomTextLink(),"Agoda price guarantee");
    }

    @Test
    public void checkZoomOut(){
        searchPage.zoomOut(6);
    }

    @Test
    public void checkZoomIn(){
        searchPage.zoomIn(3);
    }

    @Test
    public void checkZoomReset(){
        searchPage.zoomReset();
    }

    @Test
    public void checkResizeWindowSmaller(){
        searchPage.resizeWindowToSmaller();
    }

    @Test
    public void checkResizeWindowLarger(){
        searchPage.resizeWindowToLarger();
    }

    @Test
    public void checkResizeWindowReset(){
        searchPage.resizeWindowReset();
    }
}
