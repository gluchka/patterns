package smoke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ElmirHeaderForm;
import pages.ElmirMainPage;

public class DemoTest {
    private WebDriver driver = new FirefoxDriver();
    private ElmirHeaderForm elmirMainPage = new ElmirHeaderForm(driver);


    @BeforeTest
    public void loadPage() {
        driver.get("https://elmir.ua");
    }

    @Test
    public void sampleTest() {
        elmirMainPage.search("Motorola Moto X Force 32Gb Black (SM4356AE7K7)");
        // Some assertion here
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
