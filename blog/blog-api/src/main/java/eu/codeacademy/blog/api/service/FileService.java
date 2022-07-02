package eu.codeacademy.blog.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileService {
    private final Path fileLocation = Paths.get("./files").toAbsolutePath().normalize();

    public void saveFile(MultipartFile file) {
        createDirectory();
        try {
            Path filePathWithFileName = fileLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePathWithFileName, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Cannot create file", e);
            e.printStackTrace();
        }
    }

    private void createDirectory() {
        try {
            if (!Files.exists(fileLocation)) {
                Files.createDirectory(fileLocation);
            }
        } catch (IOException e) {
            log.error("Cannot create directory", e);
            e.printStackTrace();
        }
    }
}
