package ru.bookshelf.server.service.mapper.LibraryMapper;

import org.mapstruct.Mapper;
import ru.bookshelf.server.domain.entity.UploadedBook;
import ru.bookshelf.server.service.dto.UploadedBookDTO;

@Mapper
public interface LibraryMapper {
    UploadedBookDTO toDTO(UploadedBook uploadedBook);
    UploadedBook toEntity(UploadedBookDTO uploadedBookDTO);
}