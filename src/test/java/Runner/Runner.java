package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature/Test.feature", 					
		glue = "StepsDefinitions",
		tags = {"@ComprarArticulos,@logout"},
	    snippets = SnippetType.CAMELCASE
		)
public class Runner {

}
