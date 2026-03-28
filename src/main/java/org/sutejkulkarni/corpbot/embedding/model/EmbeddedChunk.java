package org.sutejkulkarni.corpbot.embedding.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;

@Getter
@AllArgsConstructor
public class EmbeddedChunk {
    private final Chunk chunk;
    private final float[] vector;
}
