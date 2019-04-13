package ru.milandr.courses.miptshop.entities.enums;

import ru.milandr.courses.miptshop.common.Constants;
import ru.milandr.courses.miptshop.common.utils.OrderStatusException;

/**
 * Enum representing possible statuses for
 * {@link ru.milandr.courses.miptshop.entities.Order} entity.
 */
public enum OrderStatus {
    CANCELED((short) -1),
    CART((short) 0),
    UNACCEPTED((short) 1),
    ACCEPTED((short) 2),
    READY((short) 3);

    private short value;

    OrderStatus(short value) {
        this.value = value;
    }

    /**
     * @return short int code of order status.
     */
    public short getValue() {
        return value;
    }


    /**
     * Translates a short int status code to an object representing
     * an {@link ru.milandr.courses.miptshop.entities.Order} status.
     *
     * @param orderStatusCode short int code of order status
     * @return an object of this class representing one of possible states
     */
    public static OrderStatus parse(short orderStatusCode) {
        OrderStatus orderStatus = null;
        for (OrderStatus item : OrderStatus.values()) {
            if (item.getValue() == orderStatusCode) {
                orderStatus = item;
                break;
            }
        }

        if (orderStatus == null) {
            throw new OrderStatusException(Constants.NO_SUCH_ORDER_STATUS + orderStatusCode);
        }

        return orderStatus;
    }
}
