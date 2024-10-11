package org.vaadin.aes.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.repository.abstracts.UserRepository;

import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        log.info("UserRepository is initialized: " + userRepository);
    }

    public User signUp(User user) {
        log.info("Signup user process is started");
        user = userRepository.save(user);
        log.info("user is registered: " + user);
        return user;
    }

    public User login(UserCredential creds) {
        log.info("Login process is started");
        return userRepository.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword());
    }

}
