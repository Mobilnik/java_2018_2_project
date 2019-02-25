package ru.milandr.courses.myshop.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.milandr.courses.myshop.entities.enums.OrderStatus;

import javax.persistence.*;


@Entity
@Table(name = "ORDERS")
@EqualsAndHashCode
@ToString
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_id_sequence")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "STATUS_CODE")
    private short statusCode;

    public OrderStatus getOrderStatus () {
        return OrderStatus.parse(this.statusCode);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }
}
