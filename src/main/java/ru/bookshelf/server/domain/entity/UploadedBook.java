package ru.bookshelf.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "library")
public class UploadedBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "author")
  private String author;

  @Column(name = "title")
  private String title;

  @Column(name = "publish_date")
  private String publish_date;

  @Column(name = "login")
  private String login;

  @Lob
  @Column(name = "file_data")
  private byte[] file_data;
}
