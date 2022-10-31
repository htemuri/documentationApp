package com.example.demo.workspace;

import com.example.demo.entities.DocumentObject;
import org.springframework.data.jpa.repository.Query;


public interface WorkspaceRepositoryCustom {
    @Query("find({ path : { $regex: ?0 } })")
    DocumentObject findObjectsByPath(String path);


}
