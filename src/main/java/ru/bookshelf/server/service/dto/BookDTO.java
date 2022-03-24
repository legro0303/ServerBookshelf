package ru.bookshelf.server.service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class BookDTO {
    long id;
    String author;
    String title;
    LocalDate publishDate;
    String owner;
    byte[] fileData;
}
