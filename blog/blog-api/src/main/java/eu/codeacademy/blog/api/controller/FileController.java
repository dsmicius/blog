package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.service.FileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@Api(tags = "File Controller")
public class FileController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public void saveFile(@RequestParam MultipartFile file) {
        fileService.saveFile(file);
    }

    @GetMapping("/file/download")
    public ResponseEntity<Resource> getFileByFileName(@RequestParam String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(fileService.getFileMediaType(fileName))
                .body(fileService.getFile(fileName));
    }
}
