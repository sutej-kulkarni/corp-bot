package org.sutejkulkarni.corpbot.prompt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatPrompt {
    private final PromptContext promptContext;
    private final SystemInstructions systemInstructions;
    private final String groundingRules;
}
