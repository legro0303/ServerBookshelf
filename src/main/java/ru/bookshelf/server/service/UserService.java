package ru.bookshelf.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bookshelf.server.domain.entity.User;
import ru.bookshelf.server.repository.UserRepository;
import ru.bookshelf.server.service.dto.UserAuthDTO;
import ru.bookshelf.server.service.dto.UserRegDTO;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registrationUser(UserRegDTO userRegDTO) {
        log.info("[UserServiceImpl.registrationUser] personDTO = {}", userRegDTO);
        User user = new User();
        userRepository.save(user
                .toBuilder()
                .firstName(userRegDTO.getFirstName())
                .secondName(userRegDTO.getSecondName())
                .login(userRegDTO.getLogin())
                .mail(userRegDTO.getMail())
                .password(userRegDTO.getPassword())
                .build());
    }

    public boolean authorizationUser(UserAuthDTO userAuthDTO) {
        log.info("[UserServiceImpl.authorizationUser] personDTO = {}", userAuthDTO);
        User user = userRepository.getFirstByLoginAndPassword(userAuthDTO.getLogin(), userAuthDTO.getPassword());
        return user != null ? true : false;
    }

    public boolean validationUser(UserAuthDTO userAuthDTO) {
        log.info("[UserServiceImpl.validationUser] personDTO = {}", userAuthDTO);
        long personCount = userRepository.countByLogin(userAuthDTO.getLogin());
        return personCount != 0 ? false : true;
    }
}
