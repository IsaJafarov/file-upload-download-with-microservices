package com.example.fileuploaddownload.service.implementation;

import com.example.fileuploaddownload.domain.Firmbase;
import com.example.fileuploaddownload.exception.FileNotFoundException;
import com.example.fileuploaddownload.properties.StorageProperties;
import com.example.fileuploaddownload.repository.FileRepository;
import com.example.fileuploaddownload.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private final StorageProperties storageProperties;
    private final FileRepository fileRepository;

    public FileServiceImpl(StorageProperties storageProperties, FileRepository fileRepository) {
        this.storageProperties = storageProperties;
        this.fileRepository = fileRepository;
    }

    @Override
    public void storeFile(MultipartFile multipartFile) {
        String filesAbsoluteLocation = storeInFolder(multipartFile);

        Firmbase firmbase = new Firmbase();
        firmbase.setLocation(filesAbsoluteLocation);
        firmbase.setVersionName( Math.random()*1000 );
        fileRepository.save(firmbase);
    }

    private String storeInFolder(MultipartFile multipartFile){
        Path rootLocation = Paths.get(storageProperties.getUploadDirectory());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if (multipartFile.isEmpty())
            throw new RuntimeException("The file is empty");
        if (fileName.contains(".."))
            throw new RuntimeException("The path is relative");

        try ( InputStream inputStream = multipartFile.getInputStream() ) {
            Files.copy(inputStream, rootLocation.resolve(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootLocation.resolve(fileName).toString();
    }

    @Override
    public Resource getFile(double versionName) {
        Firmbase firmbase = fileRepository.getByVersionName(versionName);

        Path absolutePath = Paths.get(firmbase.getLocation());
        try {
            Resource resource = new UrlResource( absolutePath.toUri() );
            if (resource.exists() && resource.isReadable())
                return resource;
            else
                throw new FileNotFoundException();

        } catch (MalformedURLException e) {
            throw new FileNotFoundException();
        }
    }

}
