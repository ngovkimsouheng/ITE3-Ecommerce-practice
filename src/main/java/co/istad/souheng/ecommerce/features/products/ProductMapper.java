package co.istad.souheng.ecommerce.features.products;

import co.istad.souheng.ecommerce.features.products.dto.CreateProductRequest;
import co.istad.souheng.ecommerce.features.products.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);
    ProductResponse mapProductRequestToProduct(Product product);
}
