package ru.milandr.courses.miptshop.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
@ToString
public class User {

    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    private Long id;

    @Column(name = "EMAIL")
    @NotNull
    @Getter
    @Setter
    private String email;

    @Column(name = "NAME")
    @NotNull
    @Getter
    @Setter
    private String name;

    @Column(name = "PHOTO")
    @Getter
    @Setter
    private byte[] photo;

    @Column(name = "PASSWORD_HASH")
    @Getter
    @Setter
    private String passwordHash;

    @Column(name = "PASSWORD_SALT")
    @Getter
    @Setter
    private String passwordSalt;

    //mappedBy - имя поля в сущности Order
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private List<Order> orders;
}
