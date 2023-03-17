package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.bookshelf.server.service.CheckErrorsService;
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
    @Autowired
    private CheckErrorsService checkErrorsService;

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userRegistration(@Validated @RequestBody UserRegDTO userRegDTO, BindingResult bindingResult) {
        String errorMessage = checkErrorsService.checkErrorsExist(bindingResult);

        if(errorMessage == null)
        {
            userService.registerUser(userRegDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping(value = "/validation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> userValidation(@Valid @RequestBody UserAuthDTO userAuthDTO, BindingResult bindingResult) {
        String errorMessage = checkErrorsService.checkErrorsExist(bindingResult);
        if(errorMessage == null)
        {
            if(!userService.validatingUser(userAuthDTO))
            {
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body("User with this login is already registered");
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
        {
            log.info("When some fields is null [{}]", ResponseEntity.status(HttpStatus.OK).body(null));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping(value = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> userAuthorization(@Valid @RequestBody UserAuthDTO userAuthDTO, BindingResult bindingResult) {
        String errorMessage = checkErrorsService.checkErrorsExist(bindingResult);

        if(errorMessage == null)
        {
            if(!userService.authorizingUser(userAuthDTO))
            {
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body("Incorrect login or password");
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
