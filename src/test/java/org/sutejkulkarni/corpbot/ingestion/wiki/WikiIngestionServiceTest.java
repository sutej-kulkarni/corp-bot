package org.sutejkulkarni.corpbot.ingestion.wiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
class WikiIngestionServiceTest {

    @Autowired
    WikiIngestionService wikiIngestionService;

    @Test
    void ingestWikiFiles_forLearning() throws Exception {
        wikiIngestionService.ingestWikiFiles();
    }
}
