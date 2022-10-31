package com.example.demo.workspace.requests;

import com.example.demo.workspace.document.DocumentFormat;

public class DocumentRequest {

    private String path;
    private DocumentFormat documentFormat;
    private String content;
    private boolean overwrite;

    public DocumentRequest() {
    }

    public DocumentRequest(String path, DocumentFormat documentFormat, String content, boolean overwrite) {
        this.path = path;
        this.documentFormat = documentFormat;
        this.content = content;
        this.overwrite = overwrite;

//        if (documentFormat.toLowerCase().equals("html")) {
//            this.documentFormat = DocumentFormat.HTML;
//        } else if (documentFormat.toLowerCase().equals("md")) {
//            this.documentFormat = DocumentFormat.MARKDOWN;
//        } else if (documentFormat.toLowerCase().equals("text")) {
//            this.documentFormat = DocumentFormat.TEXT;
//        } else {
//            this.documentFormat = DocumentFormat.PDF;
//        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    @Override
    public String toString() {
        return "DocumentRequest{" +
                "path='" + path + '\'' +
                ", documentFormat=" + documentFormat +
                ", content='" + content + '\'' +
                ", overwrite=" + overwrite +
                '}';
    }
}
