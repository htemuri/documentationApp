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

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

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
        }

        ArrayList<List> paths1 = new ArrayList<>();
        for (int i=newPaths.size()-1;i>=0;i--) {
            paths1.add(newPaths.subList(0,i+1));
        }
        for (int i=0;i<paths1.size();i++) {
            StringBuilder s = new StringBuilder("/");
            for (int j=0; j<paths1.get(i).size(); j++) {
                String x = (String) paths1.get(i).get(j);
                s.append(x).append("/");
            }
            if (!checkIfPathExists(s.toString())) {
                workspaceRepository.save(new WorkspaceObject(
                    s.toString(),
                    WorkspaceRole.DIRECTORY
            ));
            }
        }

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
        workspaceRepository.deleteAll();
    }

    public boolean checkIfPathExists(String path) {
        Query query = new Query();
        query.addCriteria(Criteria.where("path").is(path));
        return !mongoTemplate.find(query, WorkspaceObject.class).isEmpty();
    }

    public void deleteByPath(PathRequest pathRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("path").is(pathRequest.getPath()));

        char[] chars = pathRequest.getPath().toCharArray();

        if (listByPath(pathRequest).isEmpty()) {
            WorkspaceObject needToDelete =  mongoTemplate.findOne(query, WorkspaceObject.class);
//            assert needToDelete != null;
            workspaceRepository.delete(needToDelete);
        } else {
            throw new IllegalStateException("That directory has children!");
        }
    }
//    public List<Object> listByPath(PathRequest path) {
//        return Collections.singletonList(workspaceRepository.find(eq("path", Document.parse("{path: 1}"))));
//    }
}
