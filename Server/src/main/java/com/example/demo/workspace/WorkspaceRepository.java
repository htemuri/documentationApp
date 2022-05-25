package com.example.demo.workspace;

import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends MongoRepository<WorkspaceObject, String>, WorkspaceRepositoryCustom {

}
