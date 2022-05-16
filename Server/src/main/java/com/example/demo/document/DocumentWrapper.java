package com.example.demo.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Document("documentWrapper")
public class DocumentWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private DocumentRole docRole;
    private List<String> ACL;
    private String path;

    public DocumentWrapper() {
    }

    public DocumentWrapper(String name, DocumentRole docRole, List<String> ACL, String path) {
        this.name = name;
        this.docRole = docRole;
        this.ACL = ACL;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentRole getDocRole() {
        return docRole;
    }

    public void setDocRole(DocumentRole docRole) {
        this.docRole = docRole;
    }

    public List<String> getACL() {
        return ACL;
    }

    public void setACL(List<String> ACL) {
        this.ACL = ACL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentWrapper that = (DocumentWrapper) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && docRole.equals(that.docRole) && Objects.equals(ACL, that.ACL) && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, docRole, ACL, path);
    }

    @Override
    public String toString() {
        return "DocumentWrapper{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", docRole='" + docRole + '\'' +
                ", ACL=" + ACL +
                ", path='" + path + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
