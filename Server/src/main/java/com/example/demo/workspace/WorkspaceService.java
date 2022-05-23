package com.example.demo.workspace;

import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import com.example.demo.workspace.workspaceobject.WorkspaceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Service
public class WorkspaceService {


    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    // make a directory
    public void makeDirectory(PathRequest path) {

        String[] paths = path.getPath().split("\\/");
        ArrayList<String> newPaths = new ArrayList<>(Arrays.asList(paths));
        if (Objects.equals(paths[0], "")) {
            newPaths.remove(0);
            System.out.println(newPaths);
        }
        StringBuilder parents = new StringBuilder();
        for (String s : newPaths) {
            parents.append("/").append(s);
            PathRequest newDir = new PathRequest(parents.toString());
            workspaceRepository.save(new WorkspaceObject(
                    parents.toString()+"/",
                    WorkspaceRole.DIRECTORY
            ));
        }

        // TODO: take the path, convert to array delimited by '/', check  pop out top ele



    }

    // list all objects
    public List<Object> listAll() {
        return Collections.singletonList(workspaceRepository.findAll());
    }

    public List<WorkspaceObject> listByPath(PathRequest pathRequest) {
        Query query = new Query();
        char[] chars = pathRequest.getPath().toCharArray();

        if (chars[chars.length - 1] == '/') {
            query.addCriteria(Criteria.where("path").regex(pathRequest.getPath() + "[^\\/]+\\/$"));
        } else {
            query.addCriteria(Criteria.where("path").regex(pathRequest.getPath() + "\\/[^\\/]+\\/$"));
        }

        List<WorkspaceObject> result = mongoTemplate.find(query, WorkspaceObject.class);
        return result;
    }

    public void removeAllDocuments() {
        String[] paths = "/1/2/3/".split("\\/");
        ArrayList<String> newPaths = new ArrayList<>(Arrays.asList(paths));
        if (Objects.equals(paths[0], "")) {
            newPaths.remove(0);
            System.out.println(newPaths);
        }
//        System.out.println(Arrays.toString(newPaths));

        StringBuilder parents = new StringBuilder();
        for (String s : newPaths) {
            parents.append("/").append(s);
            System.out.println(parents);
        }

        workspaceRepository.deleteAll();
    }

//    public List<Object> listByPath(PathRequest path) {
//        return Collections.singletonList(workspaceRepository.find(eq("path", Document.parse("{path: 1}"))));
//    }
}
