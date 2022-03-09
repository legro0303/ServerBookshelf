package ru.bookshelf.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personal_data")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "s_name")
  private String s_name;

  @Column(name = "f_name")
  private String f_name;

  @Column(name = "login")
  private String login;

  @Column(name = "mail")
  private String mail;

  @Column(name = "password")
  private String password;
}
