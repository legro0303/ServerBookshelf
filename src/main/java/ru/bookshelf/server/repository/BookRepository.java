package ru.bookshelf.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookshelf.server.domain.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Long countByOwnerAndId(String owner, Long id);
}
