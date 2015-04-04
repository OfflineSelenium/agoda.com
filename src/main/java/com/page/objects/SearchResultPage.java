package com.page.objects;

        import com.web.coreframework.Common;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.springframework.stereotype.Component;

        import java.util.List;

@Component
public class SearchResultPage extends GeneralPage {

    @FindBy(xpath = "//strong[contains(@class,'blue')]")
    private WebElement IsSearchTextBold;

    @FindBy(xpath = "//strong[contains(@class,'blue')]")
    private WebElement SearchTextColor;

    @FindBy(xpath = "//ul[@id='interstitial-filter']//li//a")
    List<WebElement> CountMenuItems;

    @FindBy(xpath = "//ul[@id='interstitial-filter']//li//a")
    List<WebElement> MenuItems;

    @FindBy(xpath = "//ol[@id='interstitial-list']//li//strong[contains(@class,'score')]")
    List<WebElement> IsCountListHotels;

    public void load() {
        loadPage("/pages/agoda/default/page_textSearchResult.aspx?asq=7ylWkWYo99jpLRsOqDXpOpGXQ0GJg8tbKUhexgSL7xDx8M2tL0uNlw71LMayX%2bpPsQsr8xwJDjkOg7Lnhx8qBL8pWA%2bnzuVokzTeh%2b%2bb786d2V%2fWV0zlOMvF6LjJvUiL5o1%2b6Gka7CWnd8vuluCc9A%3d%3d");
    }

    public void isLoad() {
        webPageFactory.checkWeAreOnTheRightPage("Search Results for ho chi minh");
    }

    public String verifySearchText() {
        return findElementByLocator("//h1").getText();
    }

    public boolean isSearchTextBold() {
        return IsSearchTextBold.getCssValue("font-size").equals("33.8px");
    }

    public String searchTextColor() {
        String color = SearchTextColor.getCssValue("color");
        return Common.convertToHex(color);
    }

    public int countMenuItems() {
        int count = 0;
        for (WebElement list: CountMenuItems){
            count++;
        }
        return count;
    }

    public String menuItems(int i){
        return MenuItems.get(i).getText();
    }

    public int isCountListHotels(){
        int count = 0;
        for(WebElement list: IsCountListHotels){
            count++;
        }
        return count;
    }

}
