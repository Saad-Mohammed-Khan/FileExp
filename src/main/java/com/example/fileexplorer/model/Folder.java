package com.example.fileexplorer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString

@Document
public class Folder {

    @Id
    public String name;
    public String path;
    public List<String> files;

}
