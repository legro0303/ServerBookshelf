package ru.bookshelf.server.service.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class BookDTO {
    long id;
    @NotEmpty(message = "Author should not be empty")
    String author;
    @NotEmpty(message = "Title should not be empty")
    String title;
    @Past(message = "Publish date cannot be greater than the current date ")
    LocalDate publishDate;
    @NotEmpty(message = "Owner should not be empty")
    String owner;
    @NotEmpty(message = "Array of bytes doesn't contain any book")
    byte[] fileData;
}
