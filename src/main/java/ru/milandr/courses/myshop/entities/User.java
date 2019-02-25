package ru.milandr.courses.myshop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    @Id
    //generated ...
    private Long id;
    private String name;
    private String eMail;
    private String passwordHash;
    private String passwordSalt;
}
