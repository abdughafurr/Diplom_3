package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";

    // Пути для Яндекс.Браузера
    private static final String YANDEX_DRIVER_PATH = System.getProperty("user.dir") + File.separator + "yandexdriver.exe";
    private static final String YANDEX_BROWSER_PATH = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

    @Before
    public void setUp() {
        // Браузер выбирается через -Dbrowser=chrome | yandex (по умолчанию chrome)
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("yandex")) {
            driver = createYandexDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    private WebDriver createYandexDriver() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(YANDEX_DRIVER_PATH))
                .build();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER_PATH);

        return new ChromeDriver(service, options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}