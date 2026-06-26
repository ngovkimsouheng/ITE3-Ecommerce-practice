package co.istad.souheng.ecommerce.file;

import co.istad.souheng.ecommerce.file.dto.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    Optional<FileUpload> findByName(String name);
}
