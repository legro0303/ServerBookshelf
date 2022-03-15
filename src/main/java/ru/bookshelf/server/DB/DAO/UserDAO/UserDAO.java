package ru.bookshelf.server.DB.DAO.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bookshelf.server.domain.entity.User;
import ru.bookshelf.server.service.dto.UserAuthDTO;
import ru.bookshelf.server.service.dto.UserRegDTO;


import java.util.Optional;

@Component
public class UserDAO {
    @Autowired
    private UserRepository userRepository;

    public void userRegistration(UserRegDTO userRegDTO) {
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

    public Long userValidation(UserAuthDTO userAuthDTO) {
        return userRepository.countByLogin(userAuthDTO.getLogin());
    }

    public Optional<User> userAuthorization(UserAuthDTO userAuthDTO) {
        return userRepository.findByLoginAndPassword(userAuthDTO.getLogin(), userAuthDTO.getPassword());
    }
}
