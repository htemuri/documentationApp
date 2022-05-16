package com.example.demo.document;

import com.example.demo.document.file.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentWrapper, String> {

//    @Query("{docRole:'DocumentRole'}")
//    List<File> findAllFiles();
}
