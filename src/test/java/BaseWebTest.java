import drivers.InitDriver;
import initProperties.InitConfigFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public abstract class BaseWebTest {

     WebDriver driver;
     InitConfigFile configFile;

    @BeforeSuite
    public void InitPropertiesFiles() throws IOException {
        configFile = InitConfigFile.setConfigFile();
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
