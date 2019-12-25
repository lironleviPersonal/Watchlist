import drivers.InitDriver;
import initProperties.InitConfigFile;
import initProperties.InitTvShowFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public abstract class BaseTest {

    WebDriver driver;
    InitConfigFile configFile;
    InitTvShowFile watchList;

    @BeforeSuite
    public void InitPropertiesFiles() throws IOException {
        configFile = InitConfigFile.setConfigFile();
        watchList = InitTvShowFile.setWatchList();
    }

    @BeforeMethod
    public void initDriver(){
        driver = new InitDriver().setDriver();
    }

    @AfterMethod
    public void closeWebDriver(){
        driver.quit();
    }
}
