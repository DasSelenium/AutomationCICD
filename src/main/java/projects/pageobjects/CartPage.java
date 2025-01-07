package projects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import projects.abstractComponents.AbstractClass;

import java.util.List;

public class CartPage extends AbstractClass {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//li[@class='totalRow'][3]/button")
    WebElement checkout;

    public boolean validateProductInCart(String productName){

       return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public CheckOutPage checkout(){

        Actions a = new Actions(driver);
        a.moveToElement(checkout).click().build().perform();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }
}
