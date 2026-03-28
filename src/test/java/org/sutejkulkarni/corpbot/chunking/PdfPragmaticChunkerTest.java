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
class PdfPragmaticChunkerTest {

    public static final Logger log = LoggerFactory.getLogger(PdfPragmaticChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private PdfPragmaticChunker pdfPragmaticChunker;

    @Test
    void chunk_pdf_with_pragmatic_strategy() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        IngestedDocument document = documents.stream()
                .filter(doc -> doc.getSource().equals("pdf"))
                .findFirst()
                .orElseThrow();

        List<Chunk> chunks = pdfPragmaticChunker.chunks(document);

        log.info("Source: {}", document.getSource());
        log.info("Total chunks: {}", chunks.size());

        for (Chunk chunk : chunks) {
            log.info("---- Chunk {} ----", chunk.getChunkIndex());
            log.info("---- MetaData {} ----", chunk.getMetadata());
            log.info(chunk.getContent());
        }
    }
}
