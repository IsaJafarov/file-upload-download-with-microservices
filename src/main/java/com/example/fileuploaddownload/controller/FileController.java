package com.example.fileuploaddownload.controller;

import com.example.fileuploaddownload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        fileService.storeFile(multipartFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/download/{versionName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("versionName") double versionName) {
        Resource fileResource = fileService.getFile(versionName);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }

}
