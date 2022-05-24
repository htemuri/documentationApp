package com.example.demo.workspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/workspace")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping(path = "mkdirs")
    public void makeDirectory(@RequestBody PathRequest path) {
        workspaceService.makeDirectory(path);
    }

    @GetMapping(path = "list")
    public List<Object> listAll() {
        return workspaceService.listAll();
    }

    @GetMapping(path = "listByPath")
    public List<Object> listByPath(@RequestBody PathRequest path) {
        return Collections.singletonList(workspaceService.listByPath(path));
    }
    @GetMapping(path = "deleteAll")
    public void deleteAll() {
        workspaceService.removeAllDocuments();
    }

    @PostMapping(path = "delete")
    public String delete(@RequestBody PathRequest pathRequest) {
        try {
            workspaceService.deleteByPath(pathRequest);
            return "deleted";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

}
