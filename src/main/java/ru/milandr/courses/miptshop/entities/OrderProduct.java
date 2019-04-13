package ru.milandr.courses.miptshop.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS_PRODUCTS")
@IdClass(OrderProductPK.class)
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"order", "product"})
public class OrderProduct {
    @Id
    @Column(name = "ORDER_ID")
    @Getter
    @Setter
    private Long orderId;

    @Id
    @Column(name = "PRODUCT_ID")
    @Getter
    @Setter
    private Long productId;

    @Column(name = "QUANTITY")
    @Getter
    @Setter
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Product product;

    public OrderProduct(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
