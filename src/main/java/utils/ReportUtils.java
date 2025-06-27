package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.Status;

import java.io.IOException;

public class ReportUtils {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Inicializa o Extent Report - chamar no início da suíte
    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // Cria um novo teste no relatório - usar antes de executar cada cenário
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Log simples no relatório
    public static void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    // Log de erro ou falha
    public static void logFail(String message) {
        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    // Log de sucesso
    public static void logPass(String message) {
        if (test != null) {
            test.log(Status.PASS, message);
        }
    }

    // Anexa evidência PDF gerada no relatório
    public static void attachEvidence(io.restassured.response.Response response, String scenarioName) {
        try {
            String pdfPath = EvidenceUtils.takeScreenshot(response, scenarioName);
            if (test != null) {
                test.info("Evidência em PDF: <a href='" + pdfPath + "' target='_blank'>Clique aqui para abrir</a>");
            }
        } catch (IOException e) {
            if (test != null) {
                test.warning("Falha ao anexar evidência PDF: " + e.getMessage());
            }
        }
    }

    // Finaliza e gera o relatório - chamar ao final da suíte
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
