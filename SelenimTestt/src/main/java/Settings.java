import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Settings {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setUp() {
        File file = new File("src/main/resources/chromedriver1.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        //1 - Переход на сайт
        driver.get("https://www.rgs.ru/");

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
