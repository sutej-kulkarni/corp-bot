package org.sutejkulkarni.corpbot.retrieval;

import org.sutejkulkarni.corpbot.chunking.model.Chunk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

public class ChunkRankingComparator implements Comparator<Chunk> {
    @Override
    public int compare(Chunk a, Chunk b) {

            int sourceCompare = Integer.compare(sourcePriority(a), sourcePriority(b));
            if(sourceCompare != 0) return sourceCompare;

            if(isDBChunk(a) && isDBChunk(b)) {
                int tableCompare = Integer.compare(tablePriority(a), tablePriority(b));
                if(tableCompare != 0) return tableCompare;
            }

        LocalDate dateA = extractRelevantDate(a);
        LocalDate dateB = extractRelevantDate(b);

        if (dateA != null && dateB != null) {
            return dateB.compareTo(dateA);
        }

        return 0;

    }

    private LocalDate extractRelevantDate(Chunk a) {
        Map<String, Object> metadata = a.getMetadata();
        if (!"DB".equals(metadata.get("source"))) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        String table = metadata.get("table").toString();
        if ("release_notes".equals(table)) {
            return LocalDate.parse(metadata.get("releaseDate").toString(), formatter);
        }

        if ("announcements".equals(table)) {
            return LocalDate.parse(metadata.get("effectiveFrom").toString(), formatter);
        }

        return null;
    }

    private int tablePriority(Chunk a) {
        String table = a.getMetadata().get("table").toString();

        return switch (table) {
            case "release_notes" -> 1;
            case "announcements" -> 2;
            case "faqs" -> 3;
            case "department" -> 4;
            default -> 5;
        };
    }

    private boolean isDBChunk(Chunk a) {
        return "DB".equals(a.getMetadata().get("source").toString());
    }

    private int sourcePriority(Chunk a) {
        String source = a.getMetadata().get("source").toString();

        return switch (source) {
            case "pdf" -> 1;
            case "db" -> 2;
            case "wiki" -> 3;
            default -> 4;
        };
    }
}
