package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse upload(@RequestPart MultipartFile file) {
        return fileUploadService.upload(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<FileUploadResponse> uploadMultiple(
            @RequestPart MultipartFile[] files
    ) {
        return fileUploadService.uploadMultiple(List.of(files));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        fileUploadService.deleteByName(name);
    }
}