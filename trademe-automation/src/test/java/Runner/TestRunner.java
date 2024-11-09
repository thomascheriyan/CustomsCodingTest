package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/features/API", "src/test/features/UI"},
		glue = "stepDefinitions",
		plugin = {"pretty","html: cucumber-html-reports", "json:target/cucumber.json"},
		publish = true
		)
public class TestRunner { }
