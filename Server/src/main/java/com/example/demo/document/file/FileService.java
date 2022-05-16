package com.example.demo.document.file;

import com.example.demo.document.DocumentRepository;
import com.example.demo.document.DocumentRequest;
import com.example.demo.document.DocumentRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final DocumentRepository documentRepository;

    public FileService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void saveFile(File file){
        documentRepository.save(file);
    }

}
