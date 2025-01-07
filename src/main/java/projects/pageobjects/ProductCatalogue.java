package projects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projects.abstractComponents.AbstractClass;

import java.util.List;

public class ProductCatalogue extends AbstractClass {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");

    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsBy);
        return products;

    }

    public WebElement getProductByName(String productName) {
        WebElement product = getProductsList().stream().filter(products -> products.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return product;

    }

    public void selectAndAddtoCart(String productName) throws InterruptedException {

        WebElement prod = getProductByName(productName);

        prod.findElement(addToCart).click();
        waitForElementToAppear(toastContainer);
        waitForElementToDisappear(spinner);



    }

}
