package org.sutejkulkarni.corpbot.lifecycle;

import org.sutejkulkarni.corpbot.lifecycle.model.KnowledgeRequest;
import org.sutejkulkarni.corpbot.lifecycle.model.SourceType;

public class KnowledgeIdentity {

    public static String from(KnowledgeRequest request) {

        if(request.getSourceType() == SourceType.PDF) {
            return "PDF#" + request.getName();
        }

        if(request.getSourceType() == SourceType.WIKI) {
            return "WIKI#" + request.getName();
        }

        if(request.getSourceType() == SourceType.DATABASE) {
            return "DB#" + request.getName();
        }

        return "";
    }
}
