package ru.bookshelf.server.service.dto;

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
    @NotEmpty(message = "Login should not be empty")
    public String login;
    @NotEmpty(message = "Password should not be empty")
    public String password;
}
