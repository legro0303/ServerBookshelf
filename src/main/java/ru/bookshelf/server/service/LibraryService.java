package ru.bookshelf.server.service;

import org.springframework.stereotype.Service;
import ru.bookshelf.server.service.dto.UploadedBookDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;


import java.util.List;

@Service
public interface LibraryService {
  public List countOfBooks();
  public void savingBook(UploadedBookDTO uploadedBookDTO);
  public ValidationResultDTO deleteBook(UploadedBookDTO uploadedBookDTO);
}
