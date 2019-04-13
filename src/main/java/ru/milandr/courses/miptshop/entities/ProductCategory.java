package ru.milandr.courses.miptshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductCategory {

    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    private Long id;

    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;


    @OneToMany(mappedBy = "category")
    @Getter
    @Setter
    private List<Product> products;

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
