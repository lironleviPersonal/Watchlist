package imdbpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import waiterutil.Waiter;

abstract class BasePage {
        private final int defaultTimeOut = 30;
        private WebDriver driver;

        BasePage(WebDriver driver){
             this.driver = driver;
             PageFactory.initElements(driver,this);
         }

        void navigateToPage(String url){
            driver.get(url);
         }

        void clickAfterWait(WebElement element, By by) {
            Waiter.waitForElementToBeClickable(driver, element,by, defaultTimeOut);
            element.click();
        }

         void sendKeys(WebElement element, String text){
             element.sendKeys(text);
         }

         String getTextAfterWait(WebElement element, By by){
             Waiter.waitForElementToBeVisible(driver, by, defaultTimeOut);
            return element.getText();
         }
        void clearInputAfterWait(WebElement element, By by){
            Waiter.waitForElementToBeVisible(driver, by, defaultTimeOut);
            element.clear();
        }
}
