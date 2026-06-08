package co.istad.souheng.ecommerce.repository;

import co.istad.souheng.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//we want to create repo for entity Category and the primary key of category is Integer
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
}
