package com.example.demo.entities;

import com.example.demo.workspace.document.DocumentFormat;
import com.example.demo.workspace.workspaceobject.WorkspaceRole;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("files")
public class DocumentObject extends WorkspaceObject {
    private DocumentFormat documentFormat;
    private String content;


    public DocumentObject() {
    }

    public DocumentObject(DocumentFormat documentFormat, String content) {
        this.documentFormat = documentFormat;
        this.content = content;
    }

    public DocumentObject(String path, String content, DocumentFormat documentFormat) {
        super(path, WorkspaceRole.FILE);
        this.content = content;
        this.documentFormat = documentFormat;
    }



    public DocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
