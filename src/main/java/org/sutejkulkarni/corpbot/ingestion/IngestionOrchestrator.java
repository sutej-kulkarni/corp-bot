package org.sutejkulkarni.corpbot.ingestion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.ingestion.db.DatabaseIngestionService;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;
import org.sutejkulkarni.corpbot.ingestion.pdf.PdfIngestionService;
import org.sutejkulkarni.corpbot.ingestion.wiki.WikiIngestionService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestionOrchestrator {
    public final PdfIngestionService pdfIngestionService;
    public final WikiIngestionService wikiIngestionService;
    public final DatabaseIngestionService databaseIngestionService;

    public List<IngestedDocument> ingestAll() throws Exception {
        List<IngestedDocument> docs = new ArrayList<>();

        docs.addAll(pdfIngestionService.ingestPdfs());
        docs.addAll(wikiIngestionService.ingestWikiFiles());
        docs.addAll(databaseIngestionService.ingestDatabaseContent());

        return docs;
    }
}
