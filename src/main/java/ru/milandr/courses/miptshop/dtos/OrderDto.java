package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;

import javax.xml.bind.annotation.XmlTransient;
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
    private Long userId;

    @Getter
    @Setter
    private Short statusCode;

    @Getter
    @Setter
    private List<OrderGoodDto> orderGoods;

  /*  @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
   @JsonSerialize(using = DateSerializer.class)
    @Getter
    @Setter
    private LocalDateTime changeDateTime;*/

    public OrderDto(Long id, Long userId, OrderStatus orderStatus,
                   // LocalDateTime changeDateTime,
                    List<OrderGoodDto> orderGoods) {
        this.id = id;
        this.userId = userId;
        this.statusCode = orderStatus.getValue();
       // this.changeDateTime = changeDateTime;
        this.orderGoods = orderGoods;
    }

    public OrderDto(Long userId, OrderStatus orderStatus,
                   // LocalDateTime changeDateTime,
                    List<OrderGoodDto> orderGoods) {
        this.userId = userId;
        this.statusCode = orderStatus.getValue();
        //this.changeDateTime = changeDateTime;
        this.orderGoods = orderGoods;
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