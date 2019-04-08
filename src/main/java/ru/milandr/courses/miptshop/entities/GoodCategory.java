package ru.milandr.courses.miptshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GOOD_CATEGORIES")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GoodCategory {

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
    private List<Good> goods;

    public GoodCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
