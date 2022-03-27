package ru.bookshelf.server.service.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class BookDTO {
//TODO починить валидацию
    long id;
    @NotNull
    String author;
    @NotNull
    String title;
    @NotNull
    @Past
    LocalDate publishDate;
    @NotNull
    String owner;
    byte[] fileData;
}
