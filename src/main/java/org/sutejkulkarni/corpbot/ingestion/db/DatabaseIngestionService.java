package org.sutejkulkarni.corpbot.ingestion.db;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.sutejkulkarni.corpbot.ingestion.model.IngestedDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DatabaseIngestionService {
    private static final Logger log = LoggerFactory.getLogger(DatabaseIngestionService.class);
    private final JdbcTemplate jdbcTemplate;

    public List<IngestedDocument> ingestDatabaseContent() {
        List<IngestedDocument> docs = new ArrayList<>();

        docs.addAll(ingestFaqs());
        docs.addAll(ingestReleaseNotes());
        docs.addAll(ingestAnnouncements());

        return docs;
    }

    public List<IngestedDocument> ingestFaqs() {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList(
                        "SELECT id, question, answer, department, visibility FROM faqs");

        List<IngestedDocument> docs = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            String content = "Question: " + row.get("question") + "\n" + "Answer: " + row.get("answer") + "\n";

            docs.add(new IngestedDocument(
                    "db",
                    content,
                    Map.of(
                            "table", "faqs",
                            "id", row.get("id"),
                            "department", row.get("department"),
                            "visibility", row.get("visibility")
                    )));
        }

        return docs;
    }

    public List<IngestedDocument> ingestReleaseNotes() {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList(
                        "SELECT id, version, summary, details, release_date FROM release_notes");

        List<IngestedDocument> docs = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            String content = "Version: " + row.get("version") + "\n" +
                    "Summary: " + row.get("summary") + "\n" +
                    "Details: " + row.get("details") + "\n";

            docs.add(new IngestedDocument(
                    "db",
                    content,
                    Map.of(
                            "table", "release_notes",
                            "id", row.get("id"),
                            "version", row.get("version"),
                            "releaseDate" , row.get("release_date")
                    )
            ));
        }

        return docs;
    }

    public List<IngestedDocument> ingestAnnouncements() {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList("SELECT id, subject, body, category," +
                        "effective_from, effective_to, source_type FROM announcements");

        List<IngestedDocument> docs = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            String content = "Subject: " + row.get("subject") + "\n" + "Body: " + row.get("body") + "\n";

            docs.add(new IngestedDocument(
                    "db",
                    content,
                    Map.of(
                            "table", "announcements",
                            "id", row.get("id"),
                            "category", row.get("category"),
                            "effective_from", row.get("effective_from") != null? row.get("effective_from"): "",
                            "effective_to", row.get("effective_to") != null? row.get("effective_to"): "",
                            "source_type", row.get("source_type")
                    )
            ));
        }

        return docs;
    }
}
