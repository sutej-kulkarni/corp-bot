package org.sutejkulkarni.corpbot.chunking;

import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class WikiSemanticChunker {

    public List<Chunk> chunk(IngestedDocument document) {
        List<Chunk> chunks = new ArrayList<>();

        String content = document.getContent();

        String[] sections = content.split("\n(?=#+\\s)");

        int chunkIndex = 0;

        for(String section: sections) {
            String trim = section.trim();
            if(trim.isEmpty()) continue;

            Map<String, Object> chunkMetaData = new HashMap<>(document.getMetaData());
            chunkMetaData.put("chunkIndex", chunkIndex);
            chunkMetaData.put("chunkType", "WIKI_SECTION");

            chunks.add(new Chunk(
                    document.getSource(),
                    trim,
                    chunkMetaData,
                    chunkIndex
            ));

            chunkIndex++;
        }

        return chunks;
    }

}
