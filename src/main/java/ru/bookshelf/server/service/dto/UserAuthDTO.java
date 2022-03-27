package ru.bookshelf.server.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true) //Need to use builder from subclasses using login and password fields
public class UserAuthDTO {
    @NotEmpty
    public String login;
    @NotEmpty
    public String password;
}
