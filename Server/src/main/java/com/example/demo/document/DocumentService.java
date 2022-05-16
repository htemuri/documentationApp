package com.example.demo.document;

import com.example.demo.document.file.File;
import com.example.demo.document.file.FileService;
import com.example.demo.document.folder.Folder;
import com.example.demo.document.folder.FolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private FileService fileService;
    private FolderService folderService;
    private DocumentRepository documentRepository;

    public DocumentService(FileService fileService, FolderService folderService, DocumentRepository documentRepository) {
        this.fileService = fileService;
        this.folderService = folderService;
        this.documentRepository = documentRepository;
    }

    public List<DocumentWrapper> findAllDocs(){
        return documentRepository.findAll();
    }

    public String saveFile(DocumentRequest request){
        fileService.saveFile(
                new File(
                        request.getName(),
                        DocumentRole.FILE,
                        request.getACL(),
                        request.getPath(),
                        request.getBody()
                )
        );
        return "saved file";
    }

    public String saveFolder(DocumentRequest request){
        folderService.saveFolder(
                new Folder(
                        request.getName(),
                        DocumentRole.FOLDER,
                        request.getACL(),
                        request.getPath()
                )
        );
        return "saved folder";
    }

}
