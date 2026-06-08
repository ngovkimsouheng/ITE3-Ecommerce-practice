package co.istad.souheng.ecommerce.controller;

import co.istad.souheng.ecommerce.dto.CategoryResponse;
import co.istad.souheng.ecommerce.dto.CreateCategoryRequest;
import co.istad.souheng.ecommerce.service.CategoryService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
