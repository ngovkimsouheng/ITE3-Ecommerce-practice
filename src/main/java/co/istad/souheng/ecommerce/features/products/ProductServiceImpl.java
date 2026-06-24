package co.istad.souheng.ecommerce.features.products;

import co.istad.souheng.ecommerce.features.category.Category;
import co.istad.souheng.ecommerce.features.category.CategoryRepository;
import co.istad.souheng.ecommerce.features.products.dto.CreateProductRequest;
import co.istad.souheng.ecommerce.features.products.dto.ProductResponse;
import co.istad.souheng.ecommerce.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {


        //validate product name
        if (productRepository.existsByName(createProductRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }

        //validate category
        Category category = categoryRepository.findById(createProductRequest.categoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        //transfer data from Dto to Model
        Product product = productMapper.mapCreateProductRequestToProduct(createProductRequest);

        //set category
        product.setCategory(category);
        product.setCode(GenerateUtils.generateProductCode()); //ITE-3RD-1234
        product.setSlug(GenerateUtils.generateSlug(createProductRequest.name()));
        product.setIsAvailable(true);
        product.setIsDeleted(false);

        product = productRepository.save(product);

        return productMapper.mapProductRequestToProduct(product);
    }

    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {

        Sort sortById = Sort.by(
                Sort.Direction.DESC, "id"
        );
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(productMapper::mapProductRequestToProduct);
    }



}
