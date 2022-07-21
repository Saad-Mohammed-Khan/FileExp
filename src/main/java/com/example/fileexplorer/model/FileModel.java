package com.example.fileexplorer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document
public class FileModel {

@Id
private Long id;
private String name;
private String description;

}
