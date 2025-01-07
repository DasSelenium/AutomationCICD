package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import projects.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

   public WebDriver driver;
   public  LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//projects//resources//GlobalData.properties");
        prop.load(fis);
        String browser= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
       // String browser = prop.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1400,900)); //run in fullScreen

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver() ;

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;

    }


    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filepath));

        //String to hashmap JacksonDataBind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        return data;

    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){

        driver.close();
    }

    public String getScreenshot(String testCaseName , WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports" + testCaseName + ".png" ;


    }
}
