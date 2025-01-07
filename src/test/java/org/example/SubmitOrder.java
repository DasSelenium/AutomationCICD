package org.example;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import projects.pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {

    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        // LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.selectAndAddtoCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.validateProductInCart(input.get("product"));
        Assert.assertTrue(cartPage.validateProductInCart(input.get("product")));
        CheckOutPage checkOutPage = cartPage.checkout();
        //Thread.sleep(3000);
        checkOutPage.selectCountry("Ind");
        ConfirmationPage confirmationPage = checkOutPage.Submit();
        String text = confirmationPage.getTitle();
        Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = {"submitOrder"} ,retryAnalyzer = Retry.class)
    public void validateOrderInOrderPage() throws InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
        OrderPage orderPage = productCatalogue.goOrdersPage();
        Assert.assertTrue(orderPage.validateOrdersInCart("ZARA COAT 3"));

    }



    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String , String> hs = new HashMap<>();
//        hs.put("email","anshika@gmail.com");
//        hs.put("password","Iamking@000");
//        hs.put("product","ZARA COAT 3");
//
//        HashMap<String , String> hs2 = new HashMap<>();
//        hs2.put("email","shetty@gmail.com");
//        hs2.put("password","Iamking@000");
//        hs2.put("product","ADIDAS ORIGINAL");

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//DataProvider.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
