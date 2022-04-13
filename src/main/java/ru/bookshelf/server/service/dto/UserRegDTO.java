package ru.bookshelf.server.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true) //Need to use builder with fields from superclass
public class UserRegDTO extends UserAuthDTO {
    @NotEmpty(message = "First name should not be empty")
    public String firstName;
    @NotEmpty(message = "Second name should not be empty")
    public String secondName;
    @Email(message = "Not a valid mail")
    @NotEmpty(message = "Mail should not be empty")
    public String mail;
}
