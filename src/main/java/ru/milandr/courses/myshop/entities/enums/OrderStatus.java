package ru.milandr.courses.myshop.entities.enums;

public enum OrderStatus {
    UNACCEPTED((short) 0),
    ACCEPTED((short) 1),
    READY((short) 2);

    private short value;

    OrderStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static OrderStatus parse(short id) {
        OrderStatus orderStatus = null; // Default
        for (OrderStatus item : OrderStatus.values()) {
            if (item.getValue() == id) {
                orderStatus = item;
                break;
            }
        }
        return orderStatus;
    }
}
