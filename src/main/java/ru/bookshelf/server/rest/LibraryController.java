package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.bookshelf.server.domain.entity.Book;
import ru.bookshelf.server.service.LibraryService;
import ru.bookshelf.server.service.dto.BookDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag(name = "Library controller")
@RestController
@RequestMapping("book")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void savingBook(@Valid @RequestBody BookDTO bookDTO, @RequestHeader MultiValueMap<String, String> headers) {
        libraryService.savingBook(bookDTO);
    }

    ;

    @GetMapping(value = "/get")
    public List countOfBooks() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<Book> booksList = libraryService.countOfBooks();
        for (Book book : booksList) {
            BookDTO bookDTO = BookDTO
                    .builder()
                    .id(book.getId())
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .publishDate(book.getPublishDate())
                    .owner(book.getOwner())
                    .build();
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    ;

    @GetMapping(value = "/get-book-bytes/{id}")
    @ResponseBody
    public byte[] bookBytes(@PathVariable String id) {
        return libraryService.getBytes(id);
    }


    ;

    @PostMapping(
            value = "/delete",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean deleteBook(@Valid @RequestBody BookDTO bookDTO) {
        log.info("[LibraryController.deleteBook] uploadedBookDTO = {}", bookDTO);
        return libraryService.deleteBook(bookDTO);
    }

    ;
}
