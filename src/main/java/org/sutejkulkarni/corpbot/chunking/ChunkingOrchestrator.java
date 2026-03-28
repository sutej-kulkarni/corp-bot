package org.sutejkulkarni.corpbot.chunking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.List;

@Service
@RequiredArgsConstructor
class ChunkingOrchestrator {
    private final WikiSemanticChunker wikiSemanticChunker;
    private final PdfPragmaticChunker pdfPragmaticChunker;
    private final DatabaseChunker databaseChunker;

    public List<Chunk> chunk(IngestedDocument document) throws Exception {
        return switch (document.getSource()) {
            case "wiki" ->
                    wikiSemanticChunker.chunk(document);

            case "pdf" ->
                    pdfPragmaticChunker.chunk(document);

            case "db" ->
                    databaseChunker.chunk(document);

            default ->
                    throw new IllegalArgumentException("Unsupported source type: " + document.getSource());
        };
    }

}
