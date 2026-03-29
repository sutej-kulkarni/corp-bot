package org.sutejkulkarni.corpbot.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.lifecycle.model.KnowledgeRequest;
import org.sutejkulkarni.corpbot.lifecycle.model.SourceType;

@SpringBootTest
class KnowledgeIdentityTest {

    private final Logger log = LoggerFactory.getLogger(KnowledgeIdentityTest.class);

    @Test
    void testSourceIdentity() {
        KnowledgeRequest knowledgeRequest = KnowledgeRequest.builder()
                .sourceType(SourceType.PDF)
                .name("HR_Leave_Policy.pdf")
                .build();

        log.info("Identity: {}", KnowledgeIdentity.from(knowledgeRequest));
    }
}


