package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "/my-app_Cucumber/Feature/completion.feature",
    glue = "stepDefinitions",
    tags = "@todoCompletionCheck",
    plugin = {"pretty", "html:test-output"}
)
public class TestRunner {
}