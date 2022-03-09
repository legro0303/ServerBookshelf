package ru.bookshelf.server.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull @NotBlank public String s_name;
  @NotNull @NotBlank public String f_name;
  @NotNull @NotBlank public String login;
  @NotNull @NotBlank public String mail;
  @NotNull @NotBlank public String password;
}
