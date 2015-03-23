package com.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends GeneralPage {

    @FindBy(id = "SearchInput")
    private WebElement searchTextBox;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li[@class='ui-menu-promote ui-menu-item entry']")
    private WebElement firstItemInAutoComplete;

    public void load(){
        loadPage("");
    }

    public void isLoad(){
        webPageFactory.checkWeAreOnTheRightPage("Agoda.com: Smarter Hotel Booking");
    }

    public SearchPage inputText(String textinput){
        searchTextBox.sendKeys(textinput);
        return this;
    }

    public boolean selectFirstItemAutocompleteDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(firstItemInAutoComplete));
        firstItemInAutoComplete.click();
        return true;
    }
}
