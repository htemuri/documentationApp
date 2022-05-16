package com.example.demo.document;

import java.util.List;
import java.util.Objects;

public class DocumentRequest {
    private String name;
    private List<String> ACL;
    private String path;
    private String body;

    public DocumentRequest() {
    }

    public DocumentRequest(String name, DocumentRole docRole, List<String> ACL, String path) {
        this.name = name;
        this.ACL = ACL;
        this.path = path;
    }

    public DocumentRequest(String name, DocumentRole docRole, List<String> ACL, String path, String body) {
        this.name = name;
        this.ACL = ACL;
        this.path = path;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getACL() {
        return ACL;
    }

    public void setACL(List<String> ACL) {
        this.ACL = ACL;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentRequest that = (DocumentRequest) o;
        return name.equals(that.name) && ACL.equals(that.ACL) && path.equals(that.path) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ACL, path, body);
    }

    @Override
    public String toString() {
        return "DocumentRequest{" +
                "name='" + name + '\'' +
                ", ACL=" + ACL +
                ", path='" + path + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
