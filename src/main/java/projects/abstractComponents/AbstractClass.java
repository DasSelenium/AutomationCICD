package projects.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import projects.pageobjects.CartPage;
import projects.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractClass {

    WebDriver driver;
    public AbstractClass( WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cart;

    @FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOfAllElements(ele));

        Thread.sleep(3000);
    }

    public CartPage goToCartPage(){

        cart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goOrdersPage(){

        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }




}
