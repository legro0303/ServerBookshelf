package ru.bookshelf.server.service.mapper.UserMapper;

import org.mapstruct.Mapper;
import ru.bookshelf.server.domain.entity.User;
import ru.bookshelf.server.service.dto.UserDTO;

@Mapper
public interface UserMapper {
  UserDTO toDTO(User user);
  User toEntity(UserDTO userDTO);
}
