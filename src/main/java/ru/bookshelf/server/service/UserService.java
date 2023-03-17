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

    public void registerUser(UserRegDTO userRegDTO) {
        log.info("User registration with [{}] and login [{}] ", userRegDTO, userRegDTO.login);

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

    public boolean validatingUser(UserAuthDTO userAuthDTO) {
        log.info("Checking that user with login [{}] is not registered yet ", userAuthDTO.login);
        long personCount = userRepository.countByLogin(userAuthDTO.getLogin());

        return personCount == 0 ? true : false;
    }

    public boolean authorizingUser(UserAuthDTO userAuthDTO) {
        log.info("Checking that user with login [{}] is exist ", userAuthDTO.login);
        User user = userRepository.getFirstByLoginAndPassword(userAuthDTO.getLogin(), userAuthDTO.getPassword());

        return user != null ? true : false;
    }
}
