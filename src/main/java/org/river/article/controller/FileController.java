package org.river.article.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.river.article.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.filePath}")
    String filePath;


    @PostMapping("/upload")
    Result<Object> upload(@RequestParam("file") MultipartFile file) {
        String link = "";
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().getLeastSignificantBits() + substring;
            try {
                file.transferTo(new File(filePath + fileName));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("file upload failed");
            }
            try {
                String hostAddress = Inet4Address.getLocalHost().getHostAddress();
                link = "http://localhost:8080/file/download/" + fileName;
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(link);
    }

    @GetMapping("/download/{fileName}")
    void download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        FileInputStream inputStream;
        ServletOutputStream outputStream;
        Path fullFilePath = Paths.get(filePath+fileName);
        if(!fullFilePath.toFile().exists()) {
           throw new RuntimeException("file not found");
        }
        try {
            String mimeType = Files.probeContentType(fullFilePath);
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            inputStream = new FileInputStream(filePath + fileName);
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
