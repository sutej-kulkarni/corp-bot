package org.sutejkulkarni.corpbot.ingestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class IngestedDocument {
    private String source;
    private String content;
    private Map<String, Object> metaData;


}
