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
class ChunkingOrchestratorTest {

    public static final Logger log = LoggerFactory.getLogger(DatabaseChunckerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private ChunkingOrchestrator chunkingOrchestrator;

    @Test
    void testAllChunks() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();
        for (IngestedDocument document : documents) {
            List<Chunk> chunks = chunkingOrchestrator.chunk(document);

            log.info("====================================");
            log.info("SOURCE : {}", document.getSource());
            log.info("CHUNKS : {}", chunks.size());

            for (Chunk chunk : chunks) {
                log.info("Chunk index : {}", chunk.getChunkIndex());
                log.info("Metadata    : {}", chunk.getMetadata());
                log.info("Content     : {}", chunk.getContent());
            }
        }
    }
}
