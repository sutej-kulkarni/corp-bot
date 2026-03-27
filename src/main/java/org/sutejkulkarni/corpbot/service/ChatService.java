package org.sutejkulkarni.corpbot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.dto.ChatRequest;
import org.sutejkulkarni.corpbot.dto.ChatResponse;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;

    public ChatResponse chat(ChatRequest chatRequest) {
        String aiResponse = chatClient.prompt().user(chatRequest.getMessage()).call().content();

        return new ChatResponse(aiResponse);
    }

}
