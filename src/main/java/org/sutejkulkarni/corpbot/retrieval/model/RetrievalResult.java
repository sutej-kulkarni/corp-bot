package org.sutejkulkarni.corpbot.retrieval.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrievalResult {
    private List<Chunk> chunks;
}
