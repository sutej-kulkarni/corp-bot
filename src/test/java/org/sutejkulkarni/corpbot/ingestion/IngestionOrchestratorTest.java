package org.sutejkulkarni.corpbot.ingestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class IngestionOrchestratorTest {

    @Autowired
    IngestionOrchestrator ingestionOrchestrator;

    @Test
    void ingestAll() throws Exception {
        List<IngestedDocument> docs = ingestionOrchestrator.ingestAll();
        System.out.println("Total docs: " + docs.size());

        docs.forEach((doc) -> {
            System.out.println("Source: " + doc.getSource());
            System.out.println(doc.getContent());
            System.out.println("--------------");
        });

    }
}
