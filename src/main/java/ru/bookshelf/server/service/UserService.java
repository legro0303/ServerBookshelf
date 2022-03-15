package ru.bookshelf.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.DB.DAO.UserDAO.UserDAO;
import ru.bookshelf.server.service.dto.UserAuthDTO;
import ru.bookshelf.server.service.dto.UserRegDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
  private final UserDAO userDAO;

  public void registrationUser(UserRegDTO userRegDTO) {
    log.info("[UserServiceImpl.registrationUser] personDTO = {}", userRegDTO);
    userDAO.userRegistration(userRegDTO);
  }

  public ValidationResultDTO authorizationUser(UserAuthDTO userAuthDTO) {
    log.info("[UserServiceImpl.authorizationUser] personDTO = {}", userAuthDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();
    var user = userDAO.userAuthorization(userAuthDTO);

    validationResultDTO.authorization = false;
    if (user.isPresent()) {
      validationResultDTO.authorization = true;
    }
    return validationResultDTO;
  }

  public ValidationResultDTO validationUser(UserAuthDTO userAuthDTO) {
    log.info("[UserServiceImpl.validationUser] personDTO = {}", userAuthDTO);
    var personCount = userDAO.userValidation(userAuthDTO);
    ValidationResultDTO validationResultDTO = new ValidationResultDTO();

    if (personCount != 0) {
      validationResultDTO.validationLogin = false;
    } else {
      validationResultDTO.validationLogin = true;
    }
    return validationResultDTO;
  }
}
