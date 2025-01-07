package projects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projects.abstractComponents.AbstractClass;

public class LandingPage extends AbstractClass {
    WebDriver driver;
    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver , this);

    }

    @FindBy(id="userEmail")
    private WebElement uname;

    @FindBy(id="userPassword")
    private WebElement pword;

    @FindBy(id="login")
    private WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    private WebElement errorMessage;

    public ProductCatalogue loginApplication(String name , String pwd){

        uname.sendKeys(name);
        pword.sendKeys(pwd);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String geterrorText(){

        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();



    }

    public void goTo(){

        driver.get("https://rahulshettyacademy.com/client");
    }






}


