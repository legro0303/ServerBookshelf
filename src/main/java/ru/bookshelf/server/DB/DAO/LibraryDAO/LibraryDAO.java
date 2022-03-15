package ru.bookshelf.server.DB.DAO.LibraryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bookshelf.server.domain.entity.Library;
import ru.bookshelf.server.service.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryDAO {
  @Autowired private LibraryRepository libraryRepository;

  public void bookSaving(BookDTO bookDTO) {
    Library library = new Library();
    libraryRepository.save(library
            .toBuilder()
            .id(bookDTO.getId())
            .author(bookDTO.getAuthor())
            .title(bookDTO.getTitle())
            .publishDate(bookDTO.getPublishDate())
            .login(bookDTO.getOwner())
            .fileData(bookDTO.getFileData())
            .build());
  }

  public void bookDeleting(BookDTO bookDTO) {
    Library library = new Library();
    libraryRepository.delete(library
            .toBuilder()
            .id(bookDTO.getId())
            .author(bookDTO.getAuthor())
            .title(bookDTO.getTitle())
            .publishDate(bookDTO.getPublishDate())
            .login(bookDTO.getOwner())
            .fileData(bookDTO.getFileData())
            .build());
  }

  public Long countForDelete(BookDTO bookDTO) {
    return libraryRepository.countByLoginAndId(bookDTO.getOwner(), bookDTO.getId());
  }

  public List countOfBooks() {
    Iterable<Library> request;
    List<Library> booksList = new ArrayList<Library>();
    request = libraryRepository.findAll();
    request.forEach(booksList::add);

    return booksList;
  }
}
