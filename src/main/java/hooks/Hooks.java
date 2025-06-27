package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.ReportUtils;

public class Hooks {

    // Executa antes de cada cenário
    @Before
    public void beforeScenario(Scenario scenario) {
        // Inicia relatório se quiser garantir (pode ser chamado várias vezes)
        ReportUtils.initReport();

        // Cria o teste no relatório para o cenário atual
        ReportUtils.createTest(scenario.getName());
    }

    // Executa depois de cada cenário
    @After
    public void afterScenario(Scenario scenario) {
        // Finaliza o relatório após cada cenário (ou pode ser feito só no final da suíte, mas cuidado)
        ReportUtils.flushReport();
    }
}
