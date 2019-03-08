package ru.milandr.courses.miptshop.common.utils;

public class OrderStatusException extends RuntimeException {
    public OrderStatusException(String message) {
        super(message);
    }
}
