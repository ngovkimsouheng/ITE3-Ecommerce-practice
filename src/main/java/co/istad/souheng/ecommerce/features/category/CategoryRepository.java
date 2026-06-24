package co.istad.souheng.ecommerce.features.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//we want to create repo for entity Category and the primary key of category is Integer
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
    Page<Category> findAllByParentCategoryId(Integer parentId, Pageable pageable);
    List<Category> findAllByParentCategoryId(Integer parentId)  ;
}
