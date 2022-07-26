package Runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@CucumberOptions(
		features = {"src/test/resources/features/"}, 
		plugin = { "html:target/results.html", "message:target/results.ndjson" }
	)
public class RunnerTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
