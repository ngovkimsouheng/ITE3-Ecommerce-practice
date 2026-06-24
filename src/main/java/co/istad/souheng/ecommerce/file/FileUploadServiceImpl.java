package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    //upload jol file system sen
    // store in db

    /**
     * 1-> prapare file information
     * 2-> file name
     * 3 : have name hae extension  yk ah   2 ng + jol knea
     *
     */
    @Value("${file.storage-location}")
    private String storageLocation;


    @Value("${file.base-uri}")
    private String baseUri;

    @Override
    public FileUploadResponse upload(MultipartFile file) {


        String fileName = UUID.randomUUID().toString();
        //myprofile.png

        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) + 1;

        fileName += "." + ext; // we will get new unique file.extension

        //we have to create absolute path to store file :  path teang strong dae store file nus

        Path path = Paths.get(storageLocation + fileName);

        //use paramater inputstream :store streaming binary ney original file
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR + "file upload error");
        }

        return FileUploadResponse.builder()
                .name(fileName)
                .siza(file.getSize())
                .mediaType(file.getContentType())
                .uri(baseUri + fileName)
                .build();
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .toList();
    }

    @Override
    public Void deleteByName(String fileName) {

        try {

            Path path = Paths.get(storageLocation, fileName);

            if (!Files.exists(path)) {
                throw new RuntimeException("File not found: " + fileName);
            }

            Files.delete(path);

            return null;

        } catch (IOException e) {
            throw new RuntimeException(
                    HttpStatus.INTERNAL_SERVER_ERROR + " file delete error"
            );
        }
    }

}
