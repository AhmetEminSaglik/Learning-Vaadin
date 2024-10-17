package org.vaadin.aes.service.concretes.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.repository.abstracts.UserRepository;
import org.vaadin.aes.service.abstracts.user.UserService;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        log.info("UserRepository is initialized: " + userRepository);
    }

    public User signUp(User user) {
        log.info("Signup user process is started");
        if (findUserByUserName(user.getUsername()) != null) {
            log.info("Username is already in use.");
            return  null;
        }
        user = userRepository.save(user);
        log.info("user is registered: " + user);
        return user;
    }

    public User login(UserCredential creds) {
        log.info("Login process is started");
        return userRepository.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword());
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

}
