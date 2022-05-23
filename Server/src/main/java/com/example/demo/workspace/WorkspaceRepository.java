package com.example.demo.workspace;

import com.example.demo.workspace.document.DocumentObject;
import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import com.mongodb.client.MongoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends MongoRepository<WorkspaceObject, String>, WorkspaceRepositoryCustom {

}
