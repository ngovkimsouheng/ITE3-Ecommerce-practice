package co.istad.souheng.ecommerce.file;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.asm.SpringAsmInfo;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    //meta data of that picture
    private String caption;

    @Column(nullable = false)
    private Long size; // default get mao chea KB
    @Column(nullable = false)
    private String mediaType; //chea png video ... it could be mediaType


}
