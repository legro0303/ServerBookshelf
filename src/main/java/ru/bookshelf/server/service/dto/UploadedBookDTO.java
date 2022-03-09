package ru.bookshelf.server.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadedBookDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull @NotBlank public String author;
  @NotNull @NotBlank public String title;
  @NotNull @NotBlank public String publish_date;
  @NotNull @NotBlank public String login;

  @NotNull
  @NotBlank
  @Type(type = "org.hibernate.type.TextType")
  @Lob
  public byte[] file_data;
}
