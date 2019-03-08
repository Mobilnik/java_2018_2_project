package ru.milandr.courses.miptshop.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "GOODS")
@ToString(exclude = "orderGoods")
public class Good {

    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "good_seq_gen")
    @SequenceGenerator(name = "good_seq_gen", sequenceName = "good_id_sequence", allocationSize = 1)
    private Long id;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "good")
    @Getter
    @Setter
    private List<OrderGood> orderGoods;
}
