package org.sutejkulkarni.corpbot.lifecycle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.ChunkingOrchestrator;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.IngestionOrchestrator;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;
import org.sutejkulkarni.corpbot.lifecycle.model.KnowledgeRequest;
import org.sutejkulkarni.corpbot.vectorstore.ChunkVectorStoreService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeLifecycleService {

    private final ChunkVectorStoreService chunkVectorStoreService;
    private final IngestionOrchestrator ingestionOrchestrator;
    private final ChunkingOrchestrator chunkingOrchestrator;

    public void ingestAll() throws Exception {
        deleteAll();

        List<IngestedDocument> docs = ingestionOrchestrator.ingestAll();
        List<Chunk> chunksToStore = new ArrayList<>();
        for (IngestedDocument doc: docs) {
            List<Chunk> chunk = chunkingOrchestrator.chunk(doc);
            chunksToStore.addAll(chunk);
        }
        chunkVectorStoreService.store(chunksToStore);
    }

    public void ingest(KnowledgeRequest knowledgeRequest) throws Exception {
        String identity = KnowledgeIdentity.from(knowledgeRequest);
        chunkVectorStoreService.deleteByIdentity(identity);

        List<IngestedDocument> docs = ingestionOrchestrator.ingest(knowledgeRequest);
        List<Chunk> chunksToStore = new ArrayList<>();
        for (IngestedDocument doc: docs) {
            List<Chunk> chunk = chunkingOrchestrator.chunk(doc);
            chunksToStore.addAll(chunk);
        }
        chunkVectorStoreService.store(chunksToStore);
    }

    public void delete(KnowledgeRequest knowledgeRequest) {
        String identity = KnowledgeIdentity.from(knowledgeRequest);

        chunkVectorStoreService.deleteByIdentity(identity);
    }

    public void deleteAll() {
        chunkVectorStoreService.deleteAll();
    }
}
