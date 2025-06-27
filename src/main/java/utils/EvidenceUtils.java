package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import io.restassured.response.Response;

public class EvidenceUtils {

    /**
     * Gera um PDF com evidência da resposta da API.
     * @param response resposta da API RestAssured
     * @param scenarioName nome do cenário para pastas e arquivo
     * @return caminho completo do arquivo PDF gerado
     * @throws IOException se falhar na geração do arquivo
     */
    public static String takeScreenshot(Response response, String scenarioName) throws IOException {
        // Formata a data e hora atual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
        String format = formatter.format(LocalDateTime.now());

        // Sanitiza nome do cenário para evitar caracteres inválidos
        String sanitizedName = sanitizeFileName(scenarioName != null ? scenarioName : "unknown_scenario");

        // Cria pasta base para guardar evidências do cenário
        String baseFolderPath = "Evidences/API/" + sanitizedName;
        File testFolder = new File(baseFolderPath);
        if (!testFolder.exists()) {
            testFolder.mkdirs();
        }

        // Define caminho do arquivo PDF
        String filePath = baseFolderPath + "/evidences_" + format + ".pdf";

        // Cria o PDF com iText
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Adiciona conteúdo ao PDF
        document.add(new Paragraph("RESPONSE TAKESCREENSHOT")
            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
            .setFontSize(16));

        document.add(new Paragraph("\n\n"));
        document.add(new Paragraph("STATUS CODE: " + response.getStatusCode()));
        document.add(new Paragraph("RESPONSE BODY: " + response.getBody().asString()));
        document.add(new Paragraph("RESPONSE HEADERS: " + response.getHeaders().toString()));
        document.add(new Paragraph("TIMESTAMP: " + LocalDateTime.now()));

        // Fecha documento para finalizar arquivo
        document.close();

        System.out.println("Evidence generated in PDF format in the folder '" + baseFolderPath + "'!");

        // Retorna o caminho para anexar no relatório
        return filePath;
    }

    /**
     * Sanitiza o nome para uso em arquivo/pasta (remove caracteres inválidos)
     */
    private static String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[<>:\"/\\\\|?*]", "_").replaceAll("\\s+", "_");
    }
}
