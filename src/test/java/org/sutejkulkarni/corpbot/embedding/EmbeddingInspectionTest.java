package org.sutejkulkarni.corpbot.embedding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.chunking.ChunkingOrchestrator;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.embedding.model.EmbeddedChunk;
import org.sutejkulkarni.corpbot.ingestion.IngestionOrchestrator;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.List;

@SpringBootTest
class EmbeddingInspectionTest {

    public static final Logger log = LoggerFactory.getLogger(EmbeddingInspectionTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private ChunkingOrchestrator chunkingOrchestrator;

    @Autowired
    private ChunkEmbeddingService chunkEmbeddingService;

    @Test
    void testEmbedding() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();
        IngestedDocument document = documents.get(0);

        List<Chunk> chunks = chunkingOrchestrator.chunk(document);
        for (Chunk chunk : chunks) {
            EmbeddedChunk embedded = chunkEmbeddingService.embed(chunk);

            log.info("Metadata    : {}", chunk.getMetadata());
            log.info("Content     : {}", chunk.getContent());
            log.info("Embedding length : {}", embedded.getVector().length );
        }
    }
}
