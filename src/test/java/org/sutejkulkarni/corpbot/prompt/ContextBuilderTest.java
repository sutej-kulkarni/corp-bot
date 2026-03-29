package org.sutejkulkarni.corpbot.prompt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.prompt.model.PromptContext;
import org.sutejkulkarni.corpbot.retrieval.RetrievalService;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

@SpringBootTest
class ContextBuilderTest {

    private final Logger log = LoggerFactory.getLogger(ContextBuilderTest.class);

    @Autowired
    private RetrievalService retrievalService;
    @Test
    void testContextBuilder() {
        RetrievalResult result = retrievalService.retrieve("What is the work from home policy");

        PromptContext promptContext = new ContextBuilder().build(result);

        log.info("=== Loaded Context ===");
        log.info("\n{}", promptContext.getContextText());
    }
}
