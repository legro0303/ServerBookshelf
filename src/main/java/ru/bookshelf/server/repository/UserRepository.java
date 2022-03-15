package ru.bookshelf.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookshelf.server.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getFirstByLoginAndPassword(String login, String password);
    Long countByLogin(String login);
}
