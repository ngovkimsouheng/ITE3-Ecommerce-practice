package co.istad.souheng.ecommerce.features.products;

import co.istad.souheng.ecommerce.features.products.dto.CreateProductRequest;
import co.istad.souheng.ecommerce.features.products.dto.ProductResponse;
import org.springframework.data.domain.Page;


public interface ProductService {



    /**
     * Create a new product
     *
     * @param createProductRequest is requesting data for creating request product
     * @return {@link ProductResponse}
     * @author souheng
     * @since 23/june/2026
     */

    ProductResponse createNew(CreateProductRequest createProductRequest);

    /**
     * Find product by pagination
     * */
    Page<ProductResponse> findAll(int pageNumber , int pageSize);
}
