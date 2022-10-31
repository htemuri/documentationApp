package com.example.demo.entities;

import com.example.demo.workspace.workspaceobject.WorkspaceRole;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("workspace1")
public class WorkspaceObject {
    @Id
    private String id;
    private String path;
    private WorkspaceRole objectType;

    public WorkspaceObject() {
    }

    public WorkspaceObject(String path, WorkspaceRole objectType) {
        this.path = path;
        this.objectType = objectType;
    }

//    public WorkspaceObject(Long id, String path, WorkspaceRole objectType) {
//        this.id = id;
//        this.path = path;
//        this.objectType = objectType;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public WorkspaceRole getObjectType() {
        return objectType;
    }

    public void setObjectType(WorkspaceRole objectType) {
        this.objectType = objectType;
    }

    @Override
    public String toString() {
        return "WorkspaceObject{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", objectType=" + objectType +
                '}';
    }
}
