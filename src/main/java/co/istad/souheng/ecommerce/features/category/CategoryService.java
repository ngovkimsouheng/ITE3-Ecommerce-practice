package co.istad.souheng.ecommerce.features.category;

import co.istad.souheng.ecommerce.features.category.dto.CategoryResponse;
import co.istad.souheng.ecommerce.features.category.dto.CreateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> getAllCategories(int page, int size);

    CategoryResponse getCategoryById(Integer id);

    void softDeleteCategoryById(Integer id);

    void hardDeleteCategoryById(Integer id);

    CategoryResponse updateCategoryById(Integer id, CreateCategoryRequest categoryRequest);

    Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int page, int size);
}
