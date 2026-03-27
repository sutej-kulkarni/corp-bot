package org.sutejkulkarni.corpbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sutejkulkarni.corpbot.dto.ChatRequest;
import org.sutejkulkarni.corpbot.dto.ChatResponse;
import org.sutejkulkarni.corpbot.service.ChatService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }
}