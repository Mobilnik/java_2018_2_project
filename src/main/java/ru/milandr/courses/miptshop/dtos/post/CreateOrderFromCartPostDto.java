package ru.milandr.courses.miptshop.dtos.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateOrderFromCartPostDto {
    @Getter
    @Setter
    private String comment;

    public CreateOrderFromCartPostDto(String comment) {
        this.comment = comment;
    }
}
