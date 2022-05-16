package com.example.demo.document;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Documents/")
public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping()
    public List<DocumentWrapper> returnFiles(){
        return documentService.findAllDocs();
    }

    @PostMapping(path = "newFile")
    public String newFile(@RequestBody DocumentRequest request) {
        return documentService.saveFile(request);
    }

    @PostMapping(path = "newFolder")
    public String newFolder(@RequestBody DocumentRequest request) {
        return documentService.saveFolder(request);
    }
}
