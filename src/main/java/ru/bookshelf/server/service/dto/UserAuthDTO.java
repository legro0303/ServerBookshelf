package ru.bookshelf.server.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class UserAuthDTO {
    public String login;
    public String password;
}
