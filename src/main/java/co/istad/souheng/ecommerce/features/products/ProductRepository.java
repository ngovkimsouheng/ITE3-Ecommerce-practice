package co.istad.souheng.ecommerce.features.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByName(String name);

    Optional<Product> findByCode(String code);

}
