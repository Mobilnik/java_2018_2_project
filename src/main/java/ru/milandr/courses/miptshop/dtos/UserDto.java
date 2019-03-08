package ru.milandr.courses.miptshop.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private byte[] photo;

    public UserDto(Long id, String email, String name, byte[] photo) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.photo = photo;
    }
}
