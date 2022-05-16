package com.example.demo;

import com.example.demo.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
class DocumentBackendApplication {

	@Autowired
	DocumentRepository documentRepository;
	public static void main(String[] args) {
		SpringApplication.run(DocumentBackendApplication.class, args);
	}
}
