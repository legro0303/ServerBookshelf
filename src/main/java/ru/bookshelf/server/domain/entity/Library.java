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
public class Library {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "author")
  private String author;

  @Column(name = "title")
  private String title;

  @Column(name = "publish_date")
  private LocalDate publishDate;

  @Column(name = "login")
  private String login;

  @Lob
  @Column(name = "file_data")
  private byte[] fileData;
}
