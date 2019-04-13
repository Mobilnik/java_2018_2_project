package ru.milandr.courses.miptshop.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "orderProducts")
public class Product {

    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "CATEGORY_ID")
    @NotNull
    @Getter
    @Setter
    private Long categoryId;

    @Column(name = "NAME")
    @NotNull
    @Getter
    @Setter
    private String name;

    @Column(name = "PHOTO")
    @Getter
    @Setter
    private byte[] photo;

    @Column(name = "PRICE")
    @Getter
    @Setter
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @Getter
    @Setter
    private List<OrderProduct> orderProducts;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private ProductCategory category;

    public Product(Long id, String name, byte[] photo, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
    }
}
