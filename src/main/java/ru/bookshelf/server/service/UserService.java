package ru.bookshelf.server.service;

import org.springframework.stereotype.Service;
import ru.bookshelf.server.service.dto.UserDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

@Service
public interface UserService {
  public void registrationUser(UserDTO userDTO);
  public ValidationResultDTO validationUser(UserDTO userDTO);
  public ValidationResultDTO authorizationUser(UserDTO userDTO);
}
