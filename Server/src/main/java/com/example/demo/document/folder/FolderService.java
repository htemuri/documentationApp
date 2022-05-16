package com.example.demo.document.folder;

import com.example.demo.document.DocumentRepository;
import com.example.demo.document.file.File;
import org.springframework.stereotype.Service;

@Service
public class FolderService {

    private final DocumentRepository documentRepository;

    public FolderService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void saveFolder(Folder folder){
        documentRepository.save(folder);
    }
}
