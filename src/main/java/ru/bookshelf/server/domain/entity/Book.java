package ru.bookshelf.server.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Table(name = "library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private String title;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    private String owner;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;
}
