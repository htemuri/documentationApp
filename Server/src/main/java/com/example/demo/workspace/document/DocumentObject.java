package com.example.demo.workspace.document;

import com.example.demo.document.DocumentRole;
import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("files")
public class DocumentObject extends WorkspaceObject {
    private DocumentFormat documentFormat;
    private String content;

    public DocumentObject(DocumentFormat documentFormat, String content) {
        this.documentFormat = documentFormat;
        this.content = content;
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
