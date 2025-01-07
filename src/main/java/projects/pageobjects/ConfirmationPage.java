package projects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import projects.abstractComponents.AbstractClass;

public class ConfirmationPage extends AbstractClass {

    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    @FindBy(css=".hero-primary")
    WebElement title;


    public String getTitle(){

        String message = title.getText();
        return message;
    }
}
