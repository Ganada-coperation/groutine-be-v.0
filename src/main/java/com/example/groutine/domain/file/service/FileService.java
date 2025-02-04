package com.example.groutine.domain.file.service;

import com.example.groutine.domain.file.dto.FileCreateResponse;
import com.example.groutine.global.util.S3FileComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final S3FileComponent s3FileComponent;

    @Transactional
    public FileCreateResponse createFile(String domain , final MultipartFile file) {
        String imageUrl = s3FileComponent.uploadFile(domain, file);
        return new FileCreateResponse(imageUrl);
    }

    public void deleteFile(String fileUrl) {
        s3FileComponent.deleteFile(fileUrl);
    }

}
