package ru.bookshelf.server.service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class ValidationResultDTO {
  public Boolean validationLogin;
  public Boolean authorization;
  public Boolean bookSaving;
  public Boolean bookDeleted;
}
