package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ReportUtils;

public class Hooks {

	private static String scenarioName;

    @Before
    public void before(Scenario scenario) {
        scenarioName = scenario.getName();
        System.out.println(">>>>> Hook executado - cenário: " + scenarioName);
        ReportUtils.createTest(scenarioName);
    }

    public static String getScenarioName() {
        return scenarioName;
    }
}
