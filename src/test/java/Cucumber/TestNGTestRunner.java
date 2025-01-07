package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber cannot run on its own , so we have so many cucumber options given
@CucumberOptions(features="src/test/java/Cucumber" , glue = "stepDefination", monochrome = true,tags = "@Regression",
        plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {


}
