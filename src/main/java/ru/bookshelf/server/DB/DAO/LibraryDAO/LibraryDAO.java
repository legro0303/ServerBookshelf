package ru.bookshelf.server.DB.DAO.LibraryDAO;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bookshelf.server.domain.entity.UploadedBook;
import ru.bookshelf.server.service.dto.UploadedBookDTO;
import ru.bookshelf.server.service.mapper.LibraryMapper.LibraryMapper;


import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryDAO {
  private final LibraryMapper libraryMapper = Mappers.getMapper(LibraryMapper.class);

  @Autowired private LibraryRepository libraryRepository;

  public void bookSaving(UploadedBookDTO uploadedBookDTO) {
    libraryRepository.save(libraryMapper.toEntity(uploadedBookDTO));
  }

  public void bookDeleting(UploadedBookDTO uploadedBookDTO) {
    libraryRepository.delete(libraryMapper.toEntity(uploadedBookDTO));
  }

  public Long countForDelete(UploadedBookDTO uploadedBookDTO) {
    return libraryRepository.countByLoginAndId(uploadedBookDTO.getLogin(), uploadedBookDTO.getId());
  }

  public List countOfBooks() {
    Iterable<UploadedBook> request;
    List<UploadedBook> booksList = new ArrayList<UploadedBook>();
    request = libraryRepository.findAll();
    request.forEach(booksList::add);

    return booksList;
  }
}
