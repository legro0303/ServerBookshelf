package ru.bookshelf.server.DB.DAO.UserDAO;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bookshelf.server.domain.entity.User;
import ru.bookshelf.server.service.dto.UserDTO;
import ru.bookshelf.server.service.mapper.UserMapper.UserMapper;


import java.util.Optional;

@Component
public class UserDAO {
  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired private UserRepository userRepository;

  public void userRegistration(UserDTO userDTO) {
    userRepository.save(userMapper.toEntity(userDTO));
  }

  public Long userValidation(UserDTO userDTO) {
    return userRepository.countByLogin(userDTO.getLogin());
  }

  public Optional<User> userAuthorization(UserDTO userDTO) {
    return userRepository.findByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
  }
}
