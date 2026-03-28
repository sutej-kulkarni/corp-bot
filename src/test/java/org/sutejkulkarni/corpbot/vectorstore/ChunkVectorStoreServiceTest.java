package org.sutejkulkarni.corpbot.vectorstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.chunking.ChunkingOrchestrator;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.IngestionOrchestrator;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ChunkVectorStoreServiceTest {

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private ChunkingOrchestrator chunkingOrchestrator;

    @Autowired
    private ChunkVectorStoreService chunkVectorStoreService;

    @Test
    void testVectorStore() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        List<Chunk> chunkList = new ArrayList<>();

        for(IngestedDocument document: documents) {
            List<Chunk> chunks = chunkingOrchestrator.chunk(document);
            chunkList.addAll(chunks);
        }

        chunkVectorStoreService.store(chunkList);
    }
}
