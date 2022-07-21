package com.example.fileexplorer.service;

import com.example.fileexplorer.model.FileModel;
import com.example.fileexplorer.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class FileService {
    @Autowired
    private FileRepo fileRepo;

    public String save(MultipartFile file){
        System.out.println(file);
        FileModel newFile = new FileModel();
        newFile.setName(file.getOriginalFilename());
        newFile.setDescription(file.getContentType());
        fileRepo.save(newFile);
        return "File saved in DB";

    }
    public File[] tree(String filename) {
        File file = new File(filename);
        File[] dirFiles = file.listFiles();
        return dirFiles;
    }
    public List<String[]> getAll() throws IOException {
        List<String> files = new ArrayList<>();
        Path start = Paths.get("D:\\bluezorro_JAVA");
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            List<String> collect = stream
                    .map(String::valueOf)
                    .sorted()
                    .collect(Collectors.toList());
            collect.stream().forEach(item->{
                files.add(item);
            });
        }
        List<String[]> perFolder = new ArrayList<>();
        splitFiles(files,perFolder);
        return perFolder;
    }
    public void splitFiles(List<String> files ,List<String[]> perfolder ){
        files.forEach(file->{
            perfolder.add(file.split("\\\\"));
        });
    }
    public Optional<FileModel> get(Integer id){
        return fileRepo.findById(id);
    }
    public String delete(Integer id){
        fileRepo.deleteById(id);
        return "File deleted";
    }

    public void upload(MultipartFile file) throws IOException , IllegalStateException {
        file.transferTo(new java.io.File("D:\\" + file.getOriginalFilename()));
    }
}
