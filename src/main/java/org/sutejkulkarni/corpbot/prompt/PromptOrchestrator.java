package org.sutejkulkarni.corpbot.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.prompt.model.ChatPrompt;
import org.sutejkulkarni.corpbot.prompt.model.PromptContext;
import org.sutejkulkarni.corpbot.prompt.model.SystemInstructions;
import org.sutejkulkarni.corpbot.retrieval.RetrievalService;
import org.sutejkulkarni.corpbot.retrieval.model.RetrievalResult;

@Service
@RequiredArgsConstructor
public class PromptOrchestrator {

    private final RetrievalService retrievalService;

    private final ContextBuilder contextBuilder = new ContextBuilder();
    private final SystemPromptLoader systemPromptLoader = new SystemPromptLoader();
    private final GroundingPolicy groundingPolicy = new GroundingPolicy();

    public ChatPrompt build(String question) {
        RetrievalResult result = retrievalService.retrieve(question);

        PromptContext promptContext = contextBuilder.build(result);
        SystemInstructions instructions = systemPromptLoader.load();
        String rules = groundingPolicy.groundingRules(promptContext);

        return new ChatPrompt(
                promptContext,
            instructions,
            rules
        );
    }
}
