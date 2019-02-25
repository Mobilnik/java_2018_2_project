package ru.milandr.courses.myshop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "ORDERS")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {
    @Id
    //generated ...
    private Long id;
}
