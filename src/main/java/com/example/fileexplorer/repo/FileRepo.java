package com.example.fileexplorer.repo;

import com.example.fileexplorer.model.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepo extends MongoRepository<FileModel,Integer> {

}
