package com.example.fileuploaddownload.repository;

import com.example.fileuploaddownload.domain.Firmbase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Firmbase, Long> {
    Firmbase getByVersionName(double versionName);
}
