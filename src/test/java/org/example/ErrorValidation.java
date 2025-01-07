package org.example;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidation extends BaseTest {

    @Test(groups={"ErrorHandling"} , retryAnalyzer = Retry.class)
    public void errorValidation() throws IOException {
        String productName = "ZARA COAT 3";
        landingPage.loginApplication("anshika@gmail.com", "Iisking@000");
        Assert.assertEquals("Incorrect email or password." , landingPage.geterrorText());

    }
}
