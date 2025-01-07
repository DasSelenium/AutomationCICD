package stepDefination;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import projects.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinationImplementation extends BaseTest {

    public LandingPage LandingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        //Working code
        LandingPage = launchApplication();

    }

    @Given("^Logged in with usernname (.+)and Password (.+)$")
    public void Logged_in_with_usernname_name_and_Password_password(String username, String Password) {
        //workingcode
        productCatalogue = landingPage.loginApplication(username, Password);
    }

    @When("^I add the product (.+) to Cart$")
    public void When_I_add_the_product_to_Cart(String product) throws InterruptedException {

        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.selectAndAddtoCart(product);
    }

    @And("^checkout (.+) and submit the order$")
    public void checkout_productname_and_submit_the_order(String ProductName) throws InterruptedException {

        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.validateProductInCart(ProductName);
        Assert.assertTrue(cartPage.validateProductInCart(ProductName));
        CheckOutPage checkOutPage = cartPage.checkout();

        checkOutPage.selectCountry("Ind");
        confirmationPage = checkOutPage.Submit();
    }

    @Then("{string} massage is displayed on confirmation page")
    public void  massage_is_displayed_on_confirmation_page(String string){
        String text = confirmationPage.getTitle();
        Assert.assertTrue(text.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} massage is displayed")
    public void Incorrect_email_or_password_massage_is_displayed(String string){

        Assert.assertEquals(string , landingPage.geterrorText());
        driver.close();
    }
}
