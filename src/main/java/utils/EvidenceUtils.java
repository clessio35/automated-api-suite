package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import io.restassured.response.Response;

public class EvidenceUtils {

    /**
     * Gera um PDF com evidência da resposta da API.
     *
     * @param response     resposta da API
     * @param scenarioName nome do cenário
     * @return caminho do arquivo PDF
     * @throws IOException se falhar na geração do PDF
     */
    public static String takeScreenshot(Response response, String scenarioName) throws IOException {
        // Formata data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timestamp = formatter.format(LocalDateTime.now());

        String fileTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmm"));
        String sanitizedName = sanitizeFileName(scenarioName != null ? scenarioName : "unknown_scenario");

        // Caminho da pasta e do arquivo
        String baseFolderPath = "Evidences/API/" + sanitizedName;
        File testFolder = new File(baseFolderPath);
        if (!testFolder.exists()) testFolder.mkdirs();

        String filePath = baseFolderPath + "/evidences_" + fileTimestamp + ".pdf";

        // Cria PDF
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont regular = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Cabeçalho com status
        boolean isSuccess = response.getStatusCode() >= 200 && response.getStatusCode() < 300;
        String statusText = isSuccess ? "PASS" : "FAIL";

        Paragraph header = new Paragraph("TEST RESULT: " + statusText)
                .setFont(bold)
                .setFontSize(18)
                .setFontColor(isSuccess ? ColorConstants.GREEN : ColorConstants.RED)
                .setTextAlignment(TextAlignment.CENTER);

        document.add(header);

        // Informações gerais
        document.add(new Paragraph("Scenario: " + scenarioName).setFont(bold).setFontSize(14));
        document.add(new Paragraph("Date: " + timestamp).setFont(regular).setFontSize(12));
        document.add(new Paragraph("Status Code: " + response.getStatusCode()).setFont(regular).setFontSize(12));
        document.add(new Paragraph("\n"));

        // Corpo da resposta
        document.add(new Paragraph("Response Body:").setFont(bold).setFontSize(13));
        document.add(new Paragraph(response.getBody().asPrettyString()).setFont(regular).setFontSize(11));
        document.add(new Paragraph("\n"));

        // Headers
        document.add(new Paragraph("Headers:").setFont(bold).setFontSize(13));
        document.add(new Paragraph(response.getHeaders().toString()).setFont(regular).setFontSize(11));

        document.close();
        System.out.println("Evidence PDF generated at: " + filePath);

        return filePath;
    }

    /**
     * Remove caracteres inválidos para nomes de arquivos/pastas.
     */
    private static String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[<>:\"/\\\\|?*]", "_").replaceAll("\\s+", "_");
    }
}
