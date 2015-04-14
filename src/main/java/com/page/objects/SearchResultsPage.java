package com.page.objects;

import com.web.coreframework.Common;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsPage extends GeneralPage {

    @FindBy(xpath = "//div[@class='grid']//h1")
    private WebElement searchResultMessage;

    @FindBy(xpath = "//strong[contains(@class,'blue')]")
    private WebElement result;

    public String getText() {
        return result.getText();
    }

    public void load() {
        loadPage("/pages/agoda/default/page_textSearchResult.aspx?asq=7ylWkWYo99jpLRsOqDXpOpGXQ0GJg8tbKUhexgSL7xDl7GoaCf9Sbvhy5Svs1babRhhlX4zyeQTOsv2Ata%2bvFlOLEVLD1npnG5Hch7lDymmrjIGG15Tdsk6VFhA6DjZB");
    }

    public void isLoad() {
        webPageFactory.checkWeAreOnTheRightPage("Search Results for");
    }

    public String shouldSeeColorSearchQuery() {
        String color = result.getCssValue("color");
        return Common.convertToHex(color);
    }

    public String shouldSeeTextSearchResultMessage() {
        return searchResultMessage.getText();
    }

}
