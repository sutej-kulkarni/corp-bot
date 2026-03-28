package org.sutejkulkarni.corpbot.chunking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PdfPragmaticChunker {
    private static final int PDF_CHUNK_SIZE = 500;
    public static final int PDF_CHUNK_OVERLAP = 100;

    private final FixedSizeChunker fixedSizeChunker;

    public List<Chunk> chunks(IngestedDocument document) {
        List<Chunk> chunks = fixedSizeChunker.chunk(document, PDF_CHUNK_SIZE, PDF_CHUNK_OVERLAP);

        return chunks.stream()
                .map(this::enrichPdfMetaData)
                .collect(Collectors.toList());
    }

    private Chunk enrichPdfMetaData(Chunk chunk) {
        Map<String, Object> enrichedMetaData = new HashMap<>(chunk.getMetadata());

        enrichedMetaData.put("sourceType", "PDF");
        enrichedMetaData.put("chunkStrategy", "PDF_PRAGMATIC_FIXED_SIZE");
        enrichedMetaData.put("chunkSize", PDF_CHUNK_SIZE);
        enrichedMetaData.put("chunkOverlap", PDF_CHUNK_OVERLAP);

        return new Chunk(
                chunk.getSource(),
                chunk.getContent(),
                enrichedMetaData,
                chunk.getChunkIndex()
        );
    }

}
