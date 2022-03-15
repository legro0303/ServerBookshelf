package ru.bookshelf.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.DB.DAO.LibraryDAO.LibraryDAO;
import ru.bookshelf.server.domain.entity.Library;
import ru.bookshelf.server.service.dto.BookDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {
  private final LibraryDAO libraryDAO;

  public void savingBook(BookDTO bookDTO) {
    log.info("[LibraryServiceImpl.savingBook] book data = {}", bookDTO);
    libraryDAO.bookSaving(bookDTO);
  }

  public List countOfBooks() {
    List<Library> booksList = new ArrayList<Library>();
    booksList = libraryDAO.countOfBooks();
    return booksList;
  }

  public ValidationResultDTO deleteBook(BookDTO bookDTO){

    log.info("[LibraryServiceImpl.deleteBook] uploadedBookDTO = {}", bookDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();
    var delete = libraryDAO.countForDelete(bookDTO);

    if (delete == 0) {
      validationResultDTO.bookDeleted = false;
    }else{
      libraryDAO.bookDeleting(bookDTO);
      validationResultDTO.bookDeleted = true;
    }
    return validationResultDTO;
  }

}
