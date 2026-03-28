package org.sutejkulkarni.corpbot.ingestion.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PdfIngestionService {
    public static final Logger log = LoggerFactory.getLogger(PdfIngestionService.class);
    public static final String PDF_DIRECTORY = "data/pdfs";

    public List<IngestedDocument> ingestPdfs() throws Exception {
        File[] pdfFiles = new File(PDF_DIRECTORY).listFiles();

        List<IngestedDocument> docs = new ArrayList<>();

        for (File pdfFile: pdfFiles) {
            docs.add(ingestSinglePdf(pdfFile));
        }

        return docs;
    }

    private IngestedDocument ingestSinglePdf(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return new IngestedDocument("pdf", text, Map.of("fileName", pdfFile.getName()));
        }
    }
}
