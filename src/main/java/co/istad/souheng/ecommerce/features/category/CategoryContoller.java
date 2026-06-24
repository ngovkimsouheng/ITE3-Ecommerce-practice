package co.istad.souheng.ecommerce.features.category;

import co.istad.souheng.ecommerce.features.category.dto.CategoryResponse;
import co.istad.souheng.ecommerce.features.category.dto.CreateCategoryRequest;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryContoller {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        return categoryService.createNew(createCategoryRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size) {
        return categoryService.getAllCategories(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/subcategories")
    public Page<CategoryResponse> getSubCategoriesByMainId(
            @RequestParam Integer parentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return categoryService.getSubCategoriesByMainId(parentId, page, size);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void softDeleteCategory(@PathVariable Integer id) {
        categoryService.softDeleteCategoryById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDeleteCategory(@PathVariable Integer id) {
        categoryService.hardDeleteCategoryById(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id, @RequestBody CreateCategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }
}
