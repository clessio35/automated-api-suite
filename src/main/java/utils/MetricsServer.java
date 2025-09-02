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
    }
    
    public static void stop() {
        spark.Spark.stop();
        spark.Spark.awaitStop();
    }


}

