package eu.codeacademy.blog.api.service;

import eu.codeacademy.blog.api.dto.FileResponse;
import eu.codeacademy.blog.jpa.blog.repository.FileRepository;
import eu.codeacademy.blog.jpa.file.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Path fileLocation = Paths.get("./files").toAbsolutePath().normalize();

    public FileResponse saveFile(MultipartFile file) {
        createDirectory();

        try {
            String originalFileName = file.getOriginalFilename();
            int dot = originalFileName.lastIndexOf(".");
            String fileName = originalFileName.substring(0, dot);
            String fileExtention = originalFileName.substring(dot + 1);

            File savedFileInDb = fileRepository.save(
                    File.builder()
                            .fileName(fileName)
                            .fileExtension(fileExtention)
                            .size(file.getSize())
                            .mediaType(file.getContentType())
                            .build());

            Path filePathWithFileName = fileLocation.resolve(savedFileInDb.getUniqFileName());
            Files.copy(file.getInputStream(), filePathWithFileName, StandardCopyOption.REPLACE_EXISTING);

            return FileResponse.builder()
                    .originalFileName(savedFileInDb.getUniqFileName())
                    .build();

        } catch (IOException e) {
            log.error("Cannot create file", e);
            e.printStackTrace();
        }

        return null;
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

    public Resource getFile(String filename) {
        try {
            InputStream inputStream = Files.newInputStream(fileLocation.resolve(filename));
            return new InputStreamResource(inputStream);

        } catch (IOException e) {
            log.error("can't get file", e);
            e.printStackTrace();
        }

        return null;
    }

    public MediaType getFileMediaType(String fileName) {
        return MediaType.valueOf(URLConnection.guessContentTypeFromName(fileName));
    }

}
