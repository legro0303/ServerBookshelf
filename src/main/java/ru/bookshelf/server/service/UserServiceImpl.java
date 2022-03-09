package ru.bookshelf.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.DB.DAO.UserDAO.UserDAO;
import ru.bookshelf.server.DB.DAO.UserDAO.UserRepository;
import ru.bookshelf.server.service.dto.UserDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserDAO userDAO;
  private final UserRepository userRepository;

  public void registrationUser(UserDTO userDTO) {
    log.info("[UserServiceImpl.registrationUser] personDTO = {}", userDTO);
    userDAO.userRegistration(userDTO);
  }

  public ValidationResultDTO authorizationUser(UserDTO userDTO) {
    log.info("[UserServiceImpl.authorizationUser] personDTO = {}", userDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();
    var user = userDAO.userAuthorization(userDTO);

    validationResultDTO.authorization = false;
    if (user.isPresent()) {
      validationResultDTO.authorization = true;
    }
    return validationResultDTO;
  }

  public ValidationResultDTO validationUser(UserDTO userDTO) {
    log.info("[UserServiceImpl.validationUser] personDTO = {}", userDTO);
    var personCount = userDAO.userValidation(userDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();

    if (personCount != 0) {
      validationResultDTO.validationLogin = false;
    } else {
      validationResultDTO.validationLogin = true;
    }
    return validationResultDTO;
  }
}
