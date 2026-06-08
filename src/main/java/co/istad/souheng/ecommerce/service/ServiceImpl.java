package co.istad.souheng.ecommerce.service;

import co.istad.souheng.ecommerce.domain.Category;
import co.istad.souheng.ecommerce.dto.CategoryResponse;
import co.istad.souheng.ecommerce.dto.CreateCategoryRequest;
import co.istad.souheng.ecommerce.mapper.CategoryMapper;
import co.istad.souheng.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {

        log.info("createNew {}", createCategoryRequest);

        boolean isExisting = categoryRepository
                .existsByName(createCategoryRequest.name());
        if (isExisting)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category has already been used"
            );

        Category parentCategory = null;


        if (createCategoryRequest.parentCategoryId() != null) {
            parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent Category has not been found"
                    ));
        }

        Category category = categoryMapper
                .mapCreateCategoryRequestToCategory(createCategoryRequest);

        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);

        category = categoryRepository.save(category);


        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    private Category findCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category not found"
                        ));
    }
    @Override
    public Page<CategoryResponse> getAllCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return categoryRepository.findAll(pageable)
                .map(categoryMapper::mapCategoryToCategoryResponse);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {

        Category category = findCategory(id);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
    @Override
    public void softDeleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

//    @Override
//    public void softDeleteCategoryById(Integer id) {
//
//        Category category = findCategory(id);
//
//        category.setIsDeleted(true);
//
//        List<Category> subCategories =
//                categoryRepository.findAllByParentCategoryId(id);
//
//        subCategories.forEach(sub -> sub.setIsDeleted(true));
//
//        categoryRepository.save(category);
//        categoryRepository.saveAll(subCategories);
//    }

    @Override
    public void hardDeleteCategoryById(Integer id) {

        findCategory(id);

        List<Category> subCategories =
                categoryRepository.findAllByParentCategoryId(id);

        categoryRepository.deleteAll(subCategories);
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse updateCategoryById(
            Integer id,
            CreateCategoryRequest categoryRequest
    ) {

        if (id <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid category id"
            );
        }

        Category category = findCategory(id);

        if (Boolean.TRUE.equals(category.getIsDeleted())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category has been deleted"
            );
        }

        if (categoryRepository.existsByName(categoryRequest.name())
                && !category.getName().equalsIgnoreCase(categoryRequest.name())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists"
            );
        }

        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setIcon(categoryRequest.icon());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(updatedCategory);
    }

    @Override
    public Page<CategoryResponse> getSubCategoriesByMainId(
            Integer parentId,
            int page,
            int size
    ) {

        findCategory(parentId);

        Pageable pageable = PageRequest.of(page, size);

        return categoryRepository
                .findAllByParentCategoryId(parentId, pageable)
                .map(categoryMapper::mapCategoryToCategoryResponse);
    }

}
