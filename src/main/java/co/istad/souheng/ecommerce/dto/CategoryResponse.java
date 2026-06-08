package co.istad.souheng.ecommerce.dto;

import co.istad.souheng.ecommerce.domain.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDeleted ,
        CategoryResponse parentCategory

) {
}
