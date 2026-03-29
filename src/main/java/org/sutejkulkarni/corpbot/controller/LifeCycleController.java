package org.sutejkulkarni.corpbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.sutejkulkarni.corpbot.lifecycle.KnowledgeLifecycleService;
import org.sutejkulkarni.corpbot.lifecycle.model.KnowledgeRequest;

@Controller
@RequestMapping("/api/admin/lifecycle")
class LifeCycleController {

    @Autowired
    private KnowledgeLifecycleService service;

    @PostMapping("/ingest")
    public ResponseEntity<Object> ingest(@RequestBody KnowledgeRequest request) throws Exception {
        service.ingest(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ingest-all")
    public ResponseEntity<Object> ingestAll() throws Exception {
        service.ingestAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody KnowledgeRequest request) {
        service.delete(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Object> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
