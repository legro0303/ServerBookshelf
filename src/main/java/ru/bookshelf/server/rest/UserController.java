package ru.bookshelf.server.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bookshelf.server.service.UserService;
import ru.bookshelf.server.service.dto.UserDTO;
import ru.bookshelf.server.service.dto.ValidationResultDTO;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("message")
public class UserController {
  @Autowired private UserService userService;

  @RequestMapping(
      value = "/registration",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public void registrationUser(UserDTO userDTO) {
    log.info("[UserController.registrationUser] personDTO = {}", userDTO);
    userService.registrationUser(userDTO);
  }
  ;

  @RequestMapping(
      value = "/validation",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ValidationResultDTO validationUser(UserDTO userDTO) {
    log.info("[UserController.validationUser] personDTO = {}", userDTO);
    return userService.validationUser(userDTO);
  }
  ;

  @RequestMapping(
      value = "/authorization",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ValidationResultDTO authorizationUser(@Valid UserDTO userDTO) {
    log.info("[UserController.authorizationUser] personDTO = {}", userDTO);
    return userService.authorizationUser(userDTO);
  }
  ;
}
