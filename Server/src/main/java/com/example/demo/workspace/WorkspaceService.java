package com.example.demo.workspace;

import com.example.demo.workspace.document.DocumentObject;
import com.example.demo.workspace.workspaceobject.WorkspaceObject;
import com.example.demo.workspace.workspaceobject.WorkspaceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.*;


@Service
public class WorkspaceService {


    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    // format path in PathRequest
    public String pathFormat(String pathRequest) {

        char[] chars = pathRequest.toCharArray();

        if (chars[chars.length - 1] == '/') {
            return pathRequest;
        } else {
            return (pathRequest+"/");
        }
    }

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

        String formattedPath = pathFormat(pathRequest.getPath());

        Query query = new Query();

        query.addCriteria(Criteria.where("path").regex(formattedPath + "[^\\/]+\\/$"));

        List<WorkspaceObject> result = mongoTemplate.find(query, WorkspaceObject.class);
        return result;
    }

    public void removeAllDocuments() {
        workspaceRepository.deleteAll();
    }

    public boolean checkIfPathExists(String path) {
        Query query = new Query();

        String formattedPath = pathFormat(path);

        query.addCriteria(Criteria.where("path").is(formattedPath));
        if (mongoTemplate.find(query, WorkspaceObject.class).isEmpty() && mongoTemplate.find(query, DocumentObject.class).isEmpty()) {
            return false;
        } else {
            return true;
        }    }

    public void deleteByPath(PathRequest pathRequest) {

        String formattedPath = pathFormat(pathRequest.getPath());

        Query query = new Query();
        query.addCriteria(Criteria.where("path").is(formattedPath));

        if (listByPath(pathRequest).isEmpty()) {
            WorkspaceObject needToDelete =  mongoTemplate.findOne(query, WorkspaceObject.class);
            workspaceRepository.delete(needToDelete);
        } else if (!checkIfPathExists(formattedPath)){
            throw new NullPointerException("That path does not exist!");
        } else {
            throw new IllegalStateException("That directory has children!");
        }
    }

    public WorkspaceObject getPathStatus(PathRequest pathRequest) {

        boolean exists = checkIfPathExists(pathRequest.getPath());
        if (exists) {
            Query query = new Query();
            query.addCriteria(Criteria.where("path").is(pathFormat(pathRequest.getPath())));
            return mongoTemplate.findOne(query, WorkspaceObject.class);
        } else {
            throw new NullPointerException("That path does not exist!");
        }
    }

    // save a file!!!
    public String saveDocument(DocumentRequest documentRequest) throws Exception {
        // check if the path already exists:
        boolean exists = checkIfPathExists(documentRequest.getPath());
        if (exists && !documentRequest.isOverwrite()) {
            throw new Exception("This path already exists. If you would like to overwrite it, please include that in your request!");
        } else {
            workspaceRepository.save(
                    new DocumentObject(
                            documentRequest.getPath(),
                            documentRequest.getContent(),
                            documentRequest.getDocumentFormat()
                    )
            );
            return "document saved!";
        }
    }

}
