package ru.bookshelf.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.domain.entity.Book;
import ru.bookshelf.server.repository.BookRepository;
import ru.bookshelf.server.service.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

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
        List<Book> booksList = new ArrayList<Book>();
        Iterable<Book> request;
        request = bookRepository.findAll();
        request.forEach(booksList::add);
        return booksList;
    }

    public boolean deleteBook(BookDTO bookDTO) {
        log.info("[LibraryServiceImpl.deleteBook] uploadedBookDTO = {}", bookDTO);
        Book book = Book
                .builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .publishDate(bookDTO.getPublishDate())
                .owner(bookDTO.getOwner())
                .fileData(bookDTO.getFileData())
                .build();
        long delete = bookRepository.countByOwnerAndId(book.getOwner(), book.getId());
        //TODO оптимизировать
        if (delete != 0) {
            bookRepository.delete(book);
        }
        return delete == 0 ? false : true;
    }

}
