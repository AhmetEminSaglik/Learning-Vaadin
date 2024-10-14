package org.vaadin.aes.service.abstracts.user;

import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;

public interface UserService {
    User signUp(User user);
    User login(UserCredential creds);
}
