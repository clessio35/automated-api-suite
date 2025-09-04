package utils;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import static spark.Spark.*;

public class MetricsServer {
    public static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    public static void start() {
        port(8085); // porta onde vai expor as métricas
        get("/metrics", (req, res) -> {
            res.type("text/plain");
            return registry.scrape(); // formato que o Prometheus entende
        });
        System.out.println("MetricsServer iniciado na porta 8085");
        System.out.println("Metrics for prometheus: ");
        System.out.println(" test_execution_time_seconds_count ");
        System.out.println(" test_execution_time_seconds_sum");
        System.out.println(" test_execution_time_seconds_max");
        System.out.println(" test_execution_time_seconds_sum / test_execution_time_seconds_count");
        System.out.println(" test_execution_time_seconds_sum{status=\"passed\"}");
        System.out.println(" end of metrics...");
    }
    
    public static void stop() {
        spark.Spark.stop();
        spark.Spark.awaitStop();
    }
    
}

