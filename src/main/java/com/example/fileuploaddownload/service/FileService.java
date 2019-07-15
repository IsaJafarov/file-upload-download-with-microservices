package com.example.fileuploaddownload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void storeFile(MultipartFile multipartFile);
    Resource getFile(double versionName);
}
