package utils;

import io.micrometer.core.instrument.Timer;

public class MetricsUtils {

    private static final ThreadLocal<Timer.Sample> sampleThreadLocal = new ThreadLocal<>();

    public static void startSample() {
        sampleThreadLocal.set(Timer.start(MetricsServer.registry));
    }

    public static void stopSample(String testName, String status) {
        Timer.Sample sample = sampleThreadLocal.get();
        if (sample != null) {
            sample.stop(Timer.builder("test.execution.time")
                    .description("Tempo de execução de testes")
                    .tag("test_name", testName)
                    .tag("status", status)
                    .register(MetricsServer.registry));
            sampleThreadLocal.remove();
        }
    }
}
