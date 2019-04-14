package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import ru.milandr.courses.miptshop.common.serialization.LocalDateTimeDeserializer;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Short statusCode;

    @Getter
    @Setter
    private List<OrderProductDto> products;

    @Getter
    @Setter
    private String comment;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Getter
    @Setter
    private LocalDateTime updatedDateTime;

    public OrderDto(Long id,
                    OrderStatus orderStatus,
                    LocalDateTime updatedDateTime,
                    List<OrderProductDto> products,
                    String comment) {
        this.id = id;
        this.statusCode = orderStatus.getValue();
        this.updatedDateTime = updatedDateTime;
        this.products = products;
        this.comment = comment;
    }

    @JsonIgnore
    public OrderStatus getStatus() {
        return OrderStatus.parse(this.statusCode);
    }

    @JsonIgnore
    public void setStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }
}