package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ReportUtils;
import utils.MetricsServer;

@RunWith(Cucumber.class) 
@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"steps", "hooks"},
  tags= "@all"
)
public class RunnerTest {

	@BeforeClass
	public static void beforeAll() {
	    MetricsServer.start();
	    ReportUtils.initReport();
	}

	@AfterClass
	public static void afterAll() {
	    ReportUtils.flushReport();
//	    MetricsServer.stop();  // pare o servidor só depois de tudo
	}


}
