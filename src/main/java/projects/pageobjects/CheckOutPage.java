package projects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import projects.abstractComponents.AbstractClass;

public class CheckOutPage extends AbstractClass {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement selectCountry;

    By countryOptions = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

    @FindBy(xpath="(//span[@class='ng-star-inserted'])[2]")
    WebElement chooseCountry;

    @FindBy(css=".btnn.action__submit.ng-star-inserted")
    WebElement submit;

    public void selectCountry(String country) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(selectCountry,country).build().perform();
        waitForElementToAppear(countryOptions);
        a.moveToElement(chooseCountry).click().build().perform();

    }

    public ConfirmationPage Submit() {
        Actions a = new Actions(driver);
        a.moveToElement(submit).click().build().perform();
        return new ConfirmationPage(driver);
    }
}
