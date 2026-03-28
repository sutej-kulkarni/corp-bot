package org.sutejkulkarni.corpbot.ingestion.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.TimeZone;

@SpringBootTest
class DatabaseConnectivityTest {

    @Autowired
    DatabaseIngestionService databaseIngestionService;

    @Test
    void testDatabaseIngestionService() {
            databaseIngestionService.ingestFaqs();
            databaseIngestionService.ingestReleaseNotes();
            databaseIngestionService.ingestAnnouncements();
    }
}
