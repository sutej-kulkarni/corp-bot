package org.sutejkulkarni.corpbot.prompt;

import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.prompt.model.PromptContext;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

import java.util.Map;

public class ContextBuilder {
    public PromptContext build (RetrievalResult retrievalResult) {
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for(Chunk chunk: retrievalResult.getChunks()) {
            sb.append("Context: ").append(i++).append("\n");
            appendCitations(sb, chunk);
            sb.append(chunk.getContent());
            sb.append("\n\n");
        }

        return new PromptContext(sb.toString().trim());
    }

    private void appendCitations(StringBuilder sb, Chunk chunk) {
        Map<String, Object> metadata = chunk.getMetadata();
        String source = metadata.get("source").toString();

        switch(source) {
            case "pdf":
            case "wiki":
                sb.append("[")
                        .append(source)
                        .append(": ").append(metadata.get("fileName"))
                        .append("]\n");
                break;
            case "db":
                sb.append("[db: ")
                        .append(metadata.get("table")).append("#")
                        .append(metadata.get("id"))
                        .append("]\n");
                break;
        }
    }
}
