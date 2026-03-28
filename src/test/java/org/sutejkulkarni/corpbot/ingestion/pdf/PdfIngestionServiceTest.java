package org.sutejkulkarni.corpbot.ingestion.pdf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PdfIngestionServiceTest {

    @Autowired
    PdfIngestionService pdfIngestionService;

    @Test
    void ingestPdfs_forLearning() throws Exception {
        pdfIngestionService.ingestPdfs();
    }
}
