package org.sutejkulkarni.corpbot.ingestion.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WikiIngestionService {
    public static final Logger log = LoggerFactory.getLogger(WikiIngestionService.class);
    public static final String WIKI_DIRECTORY = "data/wiki";

    public List<IngestedDocument> ingestWikiFiles() throws Exception {
        File[] markdownFiles = new File(WIKI_DIRECTORY).listFiles();

        List<IngestedDocument> docs = new ArrayList<>();

        for(File markdownFile: markdownFiles) {
            docs.add(ingestSingleFile(markdownFile));
        }

        return docs;
    }

    private IngestedDocument ingestSingleFile(File markdownFile) throws IOException {
        log.info("-------- Extracting({}) ---------", markdownFile.getName());

        String content = Files.readString(markdownFile.toPath());

        log.info("-------- Wiki Content ({}) ---------", markdownFile.getName());
        log.info(content);

        return new IngestedDocument("wiki", content, Map.of("fileName", markdownFile.getName()));
    }
}
