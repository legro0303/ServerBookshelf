package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
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
    @Autowired private LibraryService libraryService;

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void savingBook(@RequestBody @Valid BookDTO bookDTO) {
        libraryService.addingBookToDB(bookDTO);
    }

    @PostMapping(value = "/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean deletingBook(@Valid @RequestBody BookDTO bookDTO) {
        return libraryService.deletingBookFromDB(bookDTO);
    }

    @GetMapping(value = "/get")
    public List gettingListOfBooks() {
        List<BookDTO> bookDTOList = libraryService.gettingBooksFromDB();
        return bookDTOList;
    }

    @GetMapping(value = "/get-bytes/{id}")
    @ResponseBody
    public byte[] gettingBookBytes(@PathVariable String id) {
        return libraryService.gettingBookBytesFromDB(id);
    }
}
