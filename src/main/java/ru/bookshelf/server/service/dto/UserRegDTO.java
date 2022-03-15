package ru.bookshelf.server.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRegDTO extends UserAuthDTO {
    public String firstName;
    public String secondName;
    public String mail;
}
