package br.com.rrsnacks.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileUploadService {
    @Value("${server.media.upload.path}")
    private String basePath;

    public String renameFile(MultipartFile imageFile) {
        String[] splitName = imageFile.getOriginalFilename().split("\\.");
        return splitName[0] + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")) + "." + splitName[1];
    }

    public boolean isValidFile(MultipartFile imageFile) {
        if (imageFile.getOriginalFilename() == null || imageFile.getOriginalFilename().isEmpty()) {
            return false;
        }
        return imageFile.getContentType().startsWith("image");
    }

    public void uploadFile(MultipartFile imageFile, String renameFile) throws FileUploadException {
        try {
            imageFile.transferTo(new File(basePath + renameFile));
        } catch (IOException e) {
            throw new FileUploadException("Failed to save file", e);
        }
    }
}
