package ru.bookshelf.server.DB.DAO.LibraryDAO;

import com.mashape.unirest.http.JsonNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bookshelf.server.domain.entity.Library;


@Repository
public interface LibraryRepository extends CrudRepository<Library, JsonNode> {
  Long countByLoginAndId(String login, Long id);
}
