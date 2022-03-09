package ru.bookshelf.server.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.bookshelf.server.domain.entity.UploadedBook;
import ru.bookshelf.server.service.LibraryService;
import ru.bookshelf.server.service.dto.UploadedBookDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("library")
public class LibraryController {
  @Autowired private LibraryService libraryService;

  @RequestMapping(
      value = "/add",
      method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public void savingBook(
      @RequestPart("author") String author,
      @RequestPart("title") String title,
      @RequestPart("publish_date") String publish_date,
      @RequestPart("login") String login,
      @RequestPart("file_data") byte[] file_data) {

    log.info("[LibraryController.savingBook] uploadedBookDTO.author = {}", author);
    log.info("[LibraryController.savingBook] uploadedBookDTO.title = {}", title);
    log.info("[LibraryController.savingBook] uploadedBookDTO.publish_date = {}", publish_date);
    log.info("[LibraryController.savingBook] uploadedBookDTO.login = {}", login);
    log.info("[LibraryController.savingBook] uploadedBookDTO.file_data = {}", file_data[0]);
    UploadedBookDTO uploadedBookDTO = new UploadedBookDTO();

    uploadedBookDTO.setAuthor(author);
    uploadedBookDTO.setTitle(title);
    uploadedBookDTO.setPublish_date(publish_date);
    uploadedBookDTO.setLogin(login);
    uploadedBookDTO.setFile_data(file_data);

    libraryService.savingBook(uploadedBookDTO);
  }
  ;

  @RequestMapping(value = "/getBooks", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
  public List countOfBooks() {

    List<UploadedBook> booksList = new ArrayList<UploadedBook>();
    booksList = libraryService.countOfBooks();
    return booksList;
  }
  ;

  @RequestMapping(
          value = "/delete",
          method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ValidationResultDTO deleteBook(UploadedBookDTO uploadedBookDTO) {
    log.info("[LibraryController.deleteBook] uploadedBookDTO = {}", uploadedBookDTO);
    return libraryService.deleteBook(uploadedBookDTO);
  }
  ;

}
