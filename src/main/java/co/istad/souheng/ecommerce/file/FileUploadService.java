package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    //upload file
    FileUploadResponse upload(MultipartFile file); //multipathfile support krubfile type

    //Delete File by name

    //Upload Multiple files
    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);
    Void deleteByName(String fileName);

}
