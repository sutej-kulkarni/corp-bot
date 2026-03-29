package org.sutejkulkarni.corpbot.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.lifecycle.model.KnowledgeRequest;
import org.sutejkulkarni.corpbot.lifecycle.model.SourceType;

@SpringBootTest
class KnowledgeLifecycleTest {

    @Autowired
    private KnowledgeLifecycleService knowledgeLifecycleService;

    @Test
    void testDeleteAll() {
        knowledgeLifecycleService.deleteAll();
    }

    @Test
    void testIngestPdf() throws Exception {
        KnowledgeRequest request = KnowledgeRequest.builder()
                .sourceType(SourceType.PDF)
                .name("HR_Leave_Policy.pdf")
                .build();
        knowledgeLifecycleService.ingest(request);
    }

    @Test
    void testIngestAll() throws Exception {
        knowledgeLifecycleService.ingestAll();
    }
}
