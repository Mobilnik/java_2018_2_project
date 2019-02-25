package ru.milandr.courses.myshop.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.milandr.courses.myshop.entities.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ORDERS")
@ToString(exclude = "user")
public class Order {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "USER_ID")
    @NotNull
    @Getter
    @Setter
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    //Поле таблицы users!
    //JoinColumn indicates that this entity is the owner of the relationship
    //(that is: the corresponding table has a column with a foreign key to the
    // referenced table), whereas the attribute mappedBy indicates that the
    // entity in this side is the inverse of the relationship
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private User user;

    @Column(name = "STATUS_CODE")
    private short statusCode;

    public OrderStatus getOrderStatus() {
        return OrderStatus.parse(this.statusCode);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }
}
