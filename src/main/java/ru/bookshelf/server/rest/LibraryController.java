package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.bookshelf.server.domain.entity.Library;
import ru.bookshelf.server.service.LibraryService;
import ru.bookshelf.server.service.dto.BookDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag(name = "Library controller")
@RestController
@RequestMapping("book")
public class LibraryController {
  @Autowired private LibraryService libraryService;

  @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public void savingBook(@Valid @RequestBody BookDTO bookDTO, @RequestHeader MultiValueMap<String, String> headers){
    libraryService.savingBook(bookDTO);
  }
  ;

  @RequestMapping(value = "/get", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
  public List countOfBooks() {

    List<Library> booksList = new ArrayList<Library>();
    booksList = libraryService.countOfBooks();
    return booksList;
  }
  ;

  @RequestMapping(
          value = "/delete",
          method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ValidationResultDTO deleteBook(BookDTO bookDTO) {
    log.info("[LibraryController.deleteBook] uploadedBookDTO = {}", bookDTO);
    return libraryService.deleteBook(bookDTO);
  }
  ;

}
