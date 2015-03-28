package com.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

    @FindBy(xpath = "//div[@class='agoda-guarantee']/a")
    private WebElement bottomLink;

    @FindBy (xpath = "html")
    private WebElement page;

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

    public String getBottomTextLink(){
        return bottomLink.getText();
    }

    public void zoomIn(int time){
        for(int i=0;i<time;i++){
            page.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
        }
    }

    public void zoomOut(int time){
        for(int i=0;i<time;i++) {
            page.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        }
    }

    public void zoomReset(){
        page.sendKeys(Keys.chord(Keys.CONTROL, "0"));
    }

    public String getSizeWindow(){
        return String.valueOf(driver.manage().window().getSize());
    }
    public void resizeWindowToSmaller(){
        Dimension smallerDimension=new Dimension(600,350);
        driver.manage().window().setSize(smallerDimension);
    }

    public void resizeWindowToLarger(){
        Dimension largerDimension=new Dimension(1500,800);
        driver.manage().window().setSize(largerDimension);
    }

    public void resizeWindowReset(){
        Dimension originalSize=new Dimension(1382,744);
        driver.manage().window().setSize(originalSize);
    }
}
