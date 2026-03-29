package org.sutejkulkarni.corpbot.prompt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.sutejkulkarni.corpbot.prompt.model.SystemInstructions;

@SpringBootTest
class SystemPromptLoaderTest {

    private final Logger log = LoggerFactory.getLogger(SystemPromptLoaderTest.class);

    @Test
    void testLoad() {
        SystemInstructions instructions = new SystemPromptLoader().load();

        log.info("=== Loaded System Prompt ===");
        log.info("\n{}", instructions.getInstructions());

    }
}
