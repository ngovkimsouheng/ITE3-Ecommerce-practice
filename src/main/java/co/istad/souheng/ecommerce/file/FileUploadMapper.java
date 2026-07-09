package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class  FileUploadMapper {
    @Value("${file.base-uri}")
    private String baseUri;

    public FileUploadResponse mapFileUploadtoFileUploadResponse(FileUpload fileUpload) {


        return FileUploadResponse.builder()
                .name(fileUpload.getName())
                .siza(fileUpload.getSize())
                .mediaType(fileUpload.getMediaType())
                .uri(baseUri + fileUpload.getName() + "." + fileUpload.getExtension())
                .build();
    }
}
