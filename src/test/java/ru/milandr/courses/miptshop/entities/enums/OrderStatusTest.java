package ru.milandr.courses.miptshop.entities.enums;

import org.junit.Test;
import ru.milandr.courses.miptshop.common.utils.OrderStatusException;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusTest {

    @Test(expected = OrderStatusException.class)
    public void parseIncorrectStatusCodeTest() {
        OrderStatus.parse((short) 10);
    }

    @Test
    public void parseCorrectStatusCodeTest() {
        OrderStatus orderStatus = OrderStatus.parse((short) 0L);
        assertThat(orderStatus).isEqualTo(OrderStatus.UNACCEPTED);

        orderStatus = OrderStatus.parse((short) 1L);
        assertThat(orderStatus).isEqualTo(OrderStatus.ACCEPTED);

        orderStatus = OrderStatus.parse((short) 2L);
        assertThat(orderStatus).isEqualTo(OrderStatus.READY);
    }
}