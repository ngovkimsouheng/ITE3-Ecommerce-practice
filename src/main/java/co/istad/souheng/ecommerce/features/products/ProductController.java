package co.istad.souheng.ecommerce.features.products;

import co.istad.souheng.ecommerce.features.products.dto.CreateProductRequest;
import co.istad.souheng.ecommerce.features.products.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createNew(createProductRequest);
    }

    @GetMapping
    public Page<ProductResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pagenumber,
            @RequestParam(required = false, defaultValue = "25") int pagesize
    ) {
        return productService.findAll(pagenumber, pagesize);
    }

}
