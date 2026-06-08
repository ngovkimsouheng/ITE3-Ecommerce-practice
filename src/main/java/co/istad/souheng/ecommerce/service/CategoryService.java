package co.istad.souheng.ecommerce.service;

import co.istad.souheng.ecommerce.dto.CategoryResponse;
import co.istad.souheng.ecommerce.dto.CreateCategoryRequest;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

}
