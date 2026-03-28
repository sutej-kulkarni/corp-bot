package org.sutejkulkarni.corpbot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.dto.ChatRequest;
import org.sutejkulkarni.corpbot.dto.ChatResponse;
import org.sutejkulkarni.corpbot.retrieval.RetrievalService;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final RetrievalService retrievalService;

    public ChatResponse chat(ChatRequest chatRequest) {

        String userMessage = chatRequest.getMessage();

        RetrievalResult result = retrievalService.retrieve(userMessage);
        String context = buildContext(result);

        String aiResponse = chatClient.prompt()
                .system(context)
                .user(chatRequest.getMessage())
                .call()
                .content();

        return new ChatResponse(aiResponse);
    }

    private String buildContext(RetrievalResult result) {
        StringBuilder sb = new StringBuilder();
        for (Chunk chunk: result.getChunks()) {
            sb.append(chunk.getContent()).append("\n\n");
        }

        return sb.toString();
    }

}
