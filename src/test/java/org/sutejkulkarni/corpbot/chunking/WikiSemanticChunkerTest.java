package org.sutejkulkarni.corpbot.chunking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.IngestionOrchestrator;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.List;

@SpringBootTest
class WikiSemanticChunkerTest {

    public static final Logger log = LoggerFactory.getLogger(WikiSemanticChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private WikiSemanticChunker wikiSemanticChunker;

    @Test
    void testChunker() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        IngestedDocument document = documents.stream()
                .filter(doc -> doc.getSource().equals("wiki"))
                .findFirst()
                .orElseThrow();

        List<Chunk> chunks = wikiSemanticChunker.chunk(document);
        printChunks(document, chunks);
    }

    private void printChunks(IngestedDocument document, List<Chunk> chunks) {
        log.info("Source: {}", document.getSource());
        log.info("Original length: {}", document.getContent().length());
        log.info("Total chunks: {}", chunks.size());

        for (Chunk chunk : chunks) {
            log.info("---- Chunk {} ----", chunk.getChunkIndex());
            log.info(chunk.getContent());
        }
    }
}
