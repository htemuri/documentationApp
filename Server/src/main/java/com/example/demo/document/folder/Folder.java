package com.example.demo.document.folder;

import com.example.demo.document.DocumentRole;
import com.example.demo.document.DocumentWrapper;
import com.example.demo.user.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("folder")
public class Folder extends DocumentWrapper {

    public Folder() {
    }

    public Folder(String name, DocumentRole docRole, List<String> ACL, String path) {
        super(name, docRole, ACL, path);
    }
}
