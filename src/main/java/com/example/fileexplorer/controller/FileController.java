package com.example.fileexplorer.controller;

import com.example.fileexplorer.model.FileModel;
import com.example.fileexplorer.model.Folder;
import com.example.fileexplorer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file")MultipartFile file){
        String msg = fileService.save(file);
        return msg;
    }

    @GetMapping("/getAll")
    public List<String[]> getAll() throws IOException {
        return fileService.getAll();
    }

    @GetMapping("/dir")
    public File[] listDirectory(@RequestParam("path")String path) throws IOException {
        File file = new File(path);
        File[] aa = fileService.tree(file.getPath());
        return aa;
    }
    @GetMapping("/get/{id}")
    public Optional<FileModel> get(@PathVariable Integer id){
        return fileService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        fileService.delete(id);
        return "File deleted";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file) throws IOException, IllegalStateException {
        System.out.println(file);
        fileService.upload(file);
        return "File uploaded to directory";
    }
}
