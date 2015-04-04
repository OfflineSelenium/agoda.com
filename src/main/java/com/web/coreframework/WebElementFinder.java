package com.web.coreframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebElementFinder {

    @Autowired
    private WebDriver driver;

    /**
     * Find element is visible on the page based on the locator and property value provided.
     */
    public WebElement findElementByLocatorID(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementByLocatorCSS(String css) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementByLocatorXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find elements are visible on the page based on the locator and property value provided.
     */
    public List<WebElement> findElementsByLocatorXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            return null;
        }
    }

}