package ru.bookshelf.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.DB.DAO.LibraryDAO.LibraryDAO;
import ru.bookshelf.server.DB.DAO.LibraryDAO.LibraryRepository;
import ru.bookshelf.server.domain.entity.UploadedBook;
import ru.bookshelf.server.service.dto.UploadedBookDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {
  private final LibraryDAO libraryDAO;
  private final LibraryRepository libraryRepository;

  public void savingBook(UploadedBookDTO uploadedBookDTO) {
    log.info("[LibraryServiceImpl.savingBook] uploadedBookDTO.author = {}", uploadedBookDTO.author);
    log.info("[LibraryServiceImpl.savingBook] uploadedBookDTO.title = {}", uploadedBookDTO.title);
    log.info("[LibraryServiceImpl.savingBook] uploadedBookDTO.login = {}", uploadedBookDTO.login);
    log.info("[LibraryServiceImpl.savingBook] uploadedBookDTO.publish_date = {}", uploadedBookDTO.publish_date);
    libraryDAO.bookSaving(uploadedBookDTO);
  }

  public List countOfBooks() {
    List<UploadedBook> booksList = new ArrayList<UploadedBook>();
    booksList = libraryDAO.countOfBooks();
    return booksList;
  }

  public ValidationResultDTO deleteBook(UploadedBookDTO uploadedBookDTO){

    log.info("[LibraryServiceImpl.deleteBook] uploadedBookDTO = {}", uploadedBookDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();
    var delete = libraryDAO.countForDelete(uploadedBookDTO);

    if (delete == 0) {
      validationResultDTO.bookDeleted = false;
    }else{
      libraryDAO.bookDeleting(uploadedBookDTO);
      validationResultDTO.bookDeleted = true;
    }
    return validationResultDTO;
  }

}
