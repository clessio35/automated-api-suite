package runner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ReportUtils;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"steps", "hooks"},
  tags= "@list-comments"
)
public class RunnerTest {

    @BeforeClass
    public static void beforeAll() {
        ReportUtils.initReport();
    }

    @AfterClass
    public static void afterAll() {
        ReportUtils.flushReport();
    }
}
