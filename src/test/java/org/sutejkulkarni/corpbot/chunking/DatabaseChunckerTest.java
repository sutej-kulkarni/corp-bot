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
class DatabaseChunckerTest {

    public static final Logger log = LoggerFactory.getLogger(DatabaseChunckerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private DatabaseChunker databaseChunker;

    @Test
    void database_chunker() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        List<IngestedDocument> dbDocuments = documents.stream()
                .filter(doc -> doc.getSource().equals("db"))
                .toList();

        for (IngestedDocument doc: dbDocuments) {
            List<Chunk> chunks = databaseChunker.chunks(doc);
            Chunk chunk = chunks.get(0);

            log.info("==== DB CHUNK ====");
            log.info("Source      : {}", chunk.getSource());
            log.info("Chunk Index : {}", chunk.getChunkIndex());
            log.info("Metadata    : {}", chunk.getMetadata());
            log.info("Content     : {}", chunk.getContent());
        }


    }
}
