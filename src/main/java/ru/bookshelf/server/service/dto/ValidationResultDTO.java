package ru.bookshelf.server.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResultDTO {
  public Boolean validationLogin;
  public Boolean authorization;
  public Boolean bookSaving;
  public Boolean bookDeleted;
}
