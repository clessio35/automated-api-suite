package utils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;

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

    public static void attachEvidence(Response response, String scenarioName) {
        try {
            String pdfPath = EvidenceUtils.takeScreenshot(response, scenarioName);

            // Caminho absoluto do relatório e do pdf
            File reportFile = new File("reports/ExtentReport.html");
            File pdfFile = new File(pdfPath);

            // Calcula caminho relativo do PDF para o relatório
            String relativePath = reportFile.getParentFile().toURI().relativize(pdfFile.toURI()).getPath();

            if (test != null) {
                test.info("Evidência em PDF: <a href='" + relativePath + "' target='_blank'>Clique aqui para abrir</a>");
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
