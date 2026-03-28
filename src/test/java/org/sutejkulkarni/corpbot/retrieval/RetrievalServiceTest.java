package org.sutejkulkarni.corpbot.retrieval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

@SpringBootTest
class RetrievalServiceTest {

    public static final Logger log = LoggerFactory.getLogger(RetrievalServiceTest.class);

    @Autowired
    private RetrievalService retrievalService;

    @Test
    void testRetrievalService() {
        RetrievalResult result = retrievalService.retrieve("What is the leave carry forward service?");

        log.info("Retrieval result - chunks found: {}" , result.getChunks().size());
        for(Chunk chunk : result.getChunks()) {
            log.info("Metadata    : {}", chunk.getMetadata());
            log.info("Content     : {}", chunk.getContent());
        }
    }
}
