package co.istad.souheng.ecommerce.mapper;

import co.istad.souheng.ecommerce.domain.Category;
import co.istad.souheng.ecommerce.dto.CategoryResponse;
import co.istad.souheng.ecommerce.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CategoryMapper {

    //    return type = target
//    parameter = source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);
}