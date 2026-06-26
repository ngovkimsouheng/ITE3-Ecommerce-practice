package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;
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


//    @Value("${file.base-uri}")
//    private String baseUri;


    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }

    private FileUploadResponse saveFile(MultipartFile file) {


        String fileName = UUID.randomUUID().toString();
        //myprofile.png

        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) + 1;

//        fileName += "." + ext; // we will get new unique file.extension

        //we have to create absolute path to store file :  path teang strong dae store file nus

        Path path = Paths.get(storageLocation + fileName + "." + ext);

        //use paramater inputstream :store streaming binary ney original file
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR + "file upload error");
        }

        //save file to db
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(fileName);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("Test file upload");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());


        return fileUploadMapper.mapFileUploadtoFileUploadResponse(fileUpload);
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteByName(String fileName) {

        FileUpload fileUpload = fileUploadRepository.findByName(fileName).orElse(null);
 fileUploadRepository.delete(fileUpload);
        Path path = Paths.get(storageLocation + fileUpload.getName() + "." + fileUpload.getExtension());
        try {
          boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted) {
                throw new RuntimeException("File not found: " + fileName);
            }





        } catch (IOException e) {
            throw new RuntimeException(
                    HttpStatus.INTERNAL_SERVER_ERROR + " file delete error"
            );
        }
    }


    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadtoFileUploadResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found"));
    }


    @Override
    public Page<FileUploadResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<FileUpload> fileUploadResponse = fileUploadRepository.findAll(pageRequest);
        return fileUploadResponse.map(fileUploadMapper::mapFileUploadtoFileUploadResponse);
    }

}
