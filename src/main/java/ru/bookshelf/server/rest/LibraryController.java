package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bookshelf.server.service.CheckErrorsService;
import ru.bookshelf.server.service.LibraryService;
import ru.bookshelf.server.service.dto.BookDTO;
import ru.bookshelf.server.service.dto.DeleteDTO;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "Library controller")
@RestController
@RequestMapping("book")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private CheckErrorsService checkErrorsService;

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savingBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        String errorMessage = checkErrorsService.checkErrorsExist(bindingResult);
        if(errorMessage == null){
            libraryService.addingBookToDB(bookDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping(value = "/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deletingBook(@Valid @RequestBody DeleteDTO deleteDTO, BindingResult bindingResult) {
        String errorMessage = checkErrorsService.checkErrorsExist(bindingResult);
        BookDTO bookDTO = BookDTO
                .builder()
                .id(deleteDTO.getId())
                .owner(deleteDTO.getOwner())
                .build();
        if(errorMessage == null){
            if(!libraryService.deletingBookFromDB(bookDTO)){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You cannot delete this book because you do not own it");
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
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
