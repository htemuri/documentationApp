package com.example.demo.workspace;

import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkspaceRepositoryCustom {
    @Query("find({ path : { $regex: ?0 } })")
    List<WorkspaceObject> findObjectsByPath(String path);
}
