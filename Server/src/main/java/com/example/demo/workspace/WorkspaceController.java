package com.example.demo.workspace;

import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
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
        } catch (IllegalStateException | NullPointerException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "get-status")
    public Object getStatus(@RequestBody PathRequest pathRequest) {
        try {
            return Collections.singletonList(workspaceService.getPathStatus(pathRequest));
        } catch (NullPointerException nullPointerException) {
            return nullPointerException.getMessage();
        }
    }

    @PostMapping(path = "import")
    public String importDoc(@RequestBody DocumentRequest documentRequest) {
        try {
            return workspaceService.saveDocument(documentRequest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
