package com.example.demo.workspace;

import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import org.springframework.data.jpa.repository.Query;


public interface WorkspaceRepositoryCustom {
    @Query("find({ path : { $regex: ?0 } })")
    WorkspaceObject findObjectsByPath(String path);


}
