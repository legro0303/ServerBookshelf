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
@SuperBuilder(toBuilder = true) //Need to use builder with fields from superclass
public class UserRegDTO extends UserAuthDTO {
    @NotEmpty
    public String firstName;
    @NotEmpty
    public String secondName;
    @NotEmpty
    public String mail;
}
