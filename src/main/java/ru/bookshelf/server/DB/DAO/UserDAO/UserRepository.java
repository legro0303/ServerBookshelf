package ru.bookshelf.server.DB.DAO.UserDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bookshelf.server.domain.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByLoginAndPassword(String login, String password);
  Long countByLogin(String login);
}
