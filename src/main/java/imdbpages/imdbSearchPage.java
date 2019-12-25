package imdbpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tvshow.TvShowInfo;

public class imdbSearchPage extends BasePage {

    private final String stringRatingContent = "span[itemprop=ratingValue]";
    private final String stringContentName = ".title_wrapper h1";
    private final String searchBoxString ="suggestion-search";
    private final String searchResultString = "//*[@id=\"react-autowhatever-1--item-0\"]//a";

    @FindBy(id=searchBoxString)
    private WebElement inputSearchBox;

    @FindBy(xpath = searchResultString)
    private WebElement searchResult;

    @FindBy(css = stringRatingContent)
    private WebElement ratingValue;

    @FindBy(css = stringContentName)
    private WebElement contentName;


    public imdbSearchPage(WebDriver driver){
        super(driver);
    }

    public void navigateToImdb(String productionUrl){
        navigateToPage(productionUrl);
    }

    public imdbSearchPage searchTvShowContent(String keyWords){
        clearInputAfterWait(inputSearchBox,By.id(searchBoxString));
        clickAfterWait(inputSearchBox,By.id(searchBoxString));
        sendKeys(inputSearchBox,keyWords);
        clickAfterWait(searchResult, By.xpath(searchResultString));
        return this;
    }

    public TvShowInfo createPotentialTvShowForWatchlist(){
        String name = getTextAfterWait(contentName,By.cssSelector(stringContentName));
        double rating = Double.parseDouble(getTextAfterWait(ratingValue, By.cssSelector(stringRatingContent)));
        return new TvShowInfo(name,rating);
    }
}
