package com.example.demo.document.file;

import com.example.demo.document.DocumentRole;
import com.example.demo.document.DocumentWrapper;
import com.example.demo.user.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
@Document("file")
public class File extends DocumentWrapper {

    private String body;

    public File() {
    }

    public File(String name, DocumentRole docRole, List<String> ACL, String path) {
        super(name, docRole, ACL, path);
    }

    public File(String name, DocumentRole docRole, List<String> ACL, String path, String body) {
        super(name, docRole, ACL, path);
        this.body = body;
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
        if (!super.equals(o)) return false;
        File file = (File) o;
        return Objects.equals(body, file.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), body);
    }

    @Override
    public String toString() {
        return "File{" +
                "body='" + body + '\'' +
                '}';
    }
}
