package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class Standalone {

//new comments added 

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        String productName = "ZARA COAT 3";

        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");


//        driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.id("login")).click();

        LandingPage lp = new LandingPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        Thread.sleep(2000);

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.xpath("//li[@class='totalRow'][3]/button"))).click().build().perform();
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
        driver.findElement(By.xpath("(//button[contains(@class , 'ta-item')])[2]")).click();
        a.moveToElement(driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"))).click().build().perform();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();


    }
}
