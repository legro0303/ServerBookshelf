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

    public void savingBook(BookDTO bookDTO) {
        log.info("[LibraryServiceImpl.savingBook] book data = {}", bookDTO);
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

    public List countOfBooks() {
        List<Book> booksList = bookRepository.findAll();
        return booksList;
    }

    public byte[] getBytes(String id) {
        byte[] qwe;
        Optional<Book> book = bookRepository.findById(Long.valueOf(id));
        qwe = book.get().getFileData();
        return qwe;
    }

    public boolean deleteBook(BookDTO bookDTO) {
        log.info("[LibraryServiceImpl.deleteBook] uploadedBookDTO = {}", bookDTO);
        Book book = Book
                .builder()
                .id(bookDTO.getId())
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .publishDate(bookDTO.getPublishDate())
                .owner(bookDTO.getOwner())
                .fileData(bookDTO.getFileData())
                .build();
        //TODO откуда клиент возьмет id в запросе ?
        long userIsOwnerOfBook = bookRepository.countByOwnerAndId(book.getOwner(), book.getId());
        //TODO оптимизировать
        if (userIsOwnerOfBook != 0) {
            bookRepository.delete(book);
        }
        return userIsOwnerOfBook == 0 ? false : true;
    }

}
