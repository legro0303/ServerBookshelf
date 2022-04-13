package ru.bookshelf.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.domain.entity.Book;
import ru.bookshelf.server.repository.BookRepository;
import ru.bookshelf.server.service.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;

    public void addingBookToDB(BookDTO bookDTO) {
        log.info("Adding book [{}] ", bookDTO);
        Book book = Book
                .builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .publishDate(bookDTO.getPublishDate())
                .owner(bookDTO.getOwner())
                .fileData(bookDTO.getFileData())
                .build();
        bookRepository.save(book);
    }

    public List gettingBooksFromDB() {
        List<Book> booksList = bookRepository.findAll();
        List<BookDTO> booksDTOList = new ArrayList<>();
        for (Book book : booksList) {
            BookDTO bookDTO = BookDTO
                    .builder()
                    .id(book.getId())
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .publishDate(book.getPublishDate())
                    .owner(book.getOwner())
                    .build();
            booksDTOList.add(bookDTO);
        }
        log.info("Return books list [{}] ", booksDTOList);
        return booksDTOList;
    }

    public byte[] gettingBookBytesFromDB(String id) {
        log.info("Getting bytes of book with id [{}] ", id);
        byte[] bookBytes;
        Optional<Book> book = bookRepository.findById(Long.valueOf(id));
        bookBytes = book.get().getFileData();
        return bookBytes;
    }

    public boolean deletingBookFromDB(BookDTO bookDTO) {
        log.info("Deleting book [{}] ", bookDTO);
        Book book = Book
                .builder()
                .id(bookDTO.getId())
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .publishDate(bookDTO.getPublishDate())
                .owner(bookDTO.getOwner())
                .fileData(bookDTO.getFileData())
                .build();
        long userIsOwnerOfBook = bookRepository.countByOwnerAndId(book.getOwner(), book.getId());
        if (userIsOwnerOfBook != 0) {
            bookRepository.delete(book);
        }
        return userIsOwnerOfBook == 0 ? false : true;
    }
}
