package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    //upload file
    FileUploadResponse upload(MultipartFile file); //multipathfile support krubfile type

    //Delete File by name

    //Upload Multiple files
    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);
    void deleteByName(String fileName);


    //fill all file
FileUploadResponse findByName(String fileName);

    Page<FileUploadResponse> findAll(int pageNumber, int pageSize);
}
