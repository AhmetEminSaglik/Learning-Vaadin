package org.vaadin.aes.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;

import java.util.logging.Logger;

@Service
public class AuthenticationService {
    private static final Logger log = Logger.getLogger(AuthenticationService.class.getName());
    private UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public User signUp(User user) {
        log.info("Signup user process is started");
        user = userService.signUp(user);
        log.info("user registered successfully: ");
        return user;
    }

    public User login(UserCredential creds) {
        log.info("Signup user process is started");
        User user = userService.login(creds);
        log.info("user found: " + user);
        return user;
    }

}
