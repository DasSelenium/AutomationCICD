package projects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import projects.abstractComponents.AbstractClass;

import java.util.List;

public class OrderPage extends AbstractClass {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']/child::td[2]")
    List<WebElement> orderPageProducts;


    public boolean validateOrdersInCart(String productName) throws InterruptedException {

        Thread.sleep(3000);
        return orderPageProducts.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
    }
}

