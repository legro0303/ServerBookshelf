package ru.bookshelf.server.DB.DAO.LibraryDAO;

import com.mashape.unirest.http.JsonNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bookshelf.server.domain.entity.UploadedBook;


@Repository
public interface LibraryRepository extends CrudRepository<UploadedBook, JsonNode> {
  Long countByLoginAndId(String login, Long id);
}
