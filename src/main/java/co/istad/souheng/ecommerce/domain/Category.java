package co.istad.souheng.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    private String description;
    private String icon;
    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    //self reference //when we want to have a parent category and sub category // we can use self reference to achieve this
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
