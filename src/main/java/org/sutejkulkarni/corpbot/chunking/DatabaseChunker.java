package org.sutejkulkarni.corpbot.chunking;

import org.springframework.stereotype.Component;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.List;

@Component
class DatabaseChunker {
    public List<Chunk> chunk(IngestedDocument document) {
        return List.of(
                new Chunk(
                        document.getSource(),
                        document.getContent(),
                        document.getMetaData(),
                        0
                )
        );
    }
}
