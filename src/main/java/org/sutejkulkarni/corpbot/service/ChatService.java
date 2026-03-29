package org.sutejkulkarni.corpbot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.chunking.model.Chunk;
import org.sutejkulkarni.corpbot.dto.ChatRequest;
import org.sutejkulkarni.corpbot.dto.ChatResponse;
import org.sutejkulkarni.corpbot.prompt.PromptOrchestrator;
import org.sutejkulkarni.corpbot.prompt.model.ChatPrompt;
import org.sutejkulkarni.corpbot.retrieval.RetrievalService;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final PromptOrchestrator promptOrchestrator;

    public ChatResponse chat(ChatRequest chatRequest) {

        String userMessage = chatRequest.getMessage();

        ChatPrompt chatPrompt = promptOrchestrator.build(userMessage);

        String llmInput = chatPrompt.getGroundingRules()
                + "\n\n"
                + chatPrompt.getPromptContext().getContextText()
                + "\n\nUser Question:\n"
                + userMessage;

        String aiResponse = chatClient.prompt()
                .system(chatPrompt.getSystemInstructions().getInstructions())
                .user(llmInput)
                .call().content();

        return new ChatResponse(aiResponse);
    }
}
