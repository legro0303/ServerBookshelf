package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.bookshelf.server.service.UserService;
import ru.bookshelf.server.service.dto.UserAuthDTO;
import ru.bookshelf.server.service.dto.UserRegDTO;

import javax.validation.Valid;

@Slf4j
@Tag(name = "User controller")
@RestController
@RequestMapping("message")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void registrationUser(UserRegDTO userRegDTO) {
        log.info("[UserController.registrationUser] personDTO = {}", userRegDTO);
        userService.registrationUser(userRegDTO);
    }

    ;

    @RequestMapping(
            value = "/validation",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean validationUser(UserAuthDTO userAuthDTO) {
        log.info("[UserController.validationUser] personDTO = {}", userAuthDTO);
        return userService.validationUser(userAuthDTO);
    }

    ;

    @RequestMapping(
            value = "/authorization",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> authorizationUser(@Valid UserAuthDTO userAuthDTO) {
        log.info("[UserController.authorizationUser] personDTO = {}", userAuthDTO);
        log.info("[UserController.authorizationUser] personDTO = {}", userService.authorizationUser(userAuthDTO));
        if (userService.authorizationUser(userAuthDTO)) {
            return Flux.just("ok");
        } else {
            return Flux.just("bad_request");
        }
    }
}
