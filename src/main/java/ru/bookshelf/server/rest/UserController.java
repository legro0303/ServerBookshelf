package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bookshelf.server.service.UserService;
import ru.bookshelf.server.service.dto.UserAuthDTO;
import ru.bookshelf.server.service.dto.UserRegDTO;

import javax.validation.Valid;

@Slf4j
@Tag(name = "User controller")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void registrationUser(@Valid @RequestBody UserRegDTO userRegDTO) {
        log.info("[UserController.registrationUser] personDTO = {}", userRegDTO);
        userService.registrationUser(userRegDTO);
    }

    ;

    @PostMapping(
            value = "/validation",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean validationUser(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        log.info("[UserController.validationUser] personDTO = {}", userAuthDTO);
        return userService.validationUser(userAuthDTO);
    }
    ;

    @PostMapping(
            value = "/authorization",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean authorizationUser(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        log.info("[UserController.authorizationUser] personDTO = {}", userAuthDTO);
        return userService.authorizationUser(userAuthDTO);
    }
}
