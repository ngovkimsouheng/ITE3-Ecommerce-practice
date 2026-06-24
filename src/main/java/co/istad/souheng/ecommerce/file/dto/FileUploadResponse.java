package co.istad.souheng.ecommerce.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,
        String caption,
        Long siza,
        String mediaType,
        String uri
) {

}
