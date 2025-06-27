package runner;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"steps", "hooks"},
  tags= "@list-products"
)
public class RunnerTest {

    @BeforeClass
    public static void beforeAll() {
        utils.ReportUtils.initReport();
    }

    @AfterClass
    public static void afterAll() {
        utils.ReportUtils.flushReport();
    }
}
