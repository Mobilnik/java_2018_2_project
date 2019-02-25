package ru.milandr.courses.myshop.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "GOODS")
@EqualsAndHashCode
@ToString
public class Good {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "good_seq_gen")
    @SequenceGenerator(name = "good_seq_gen", sequenceName = "good_id_sequence")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "PRICE")
    private BigDecimal price;
}
