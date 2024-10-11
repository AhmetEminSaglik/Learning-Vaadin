/*
package org.vaadin.aes.repository.concretes;

import org.springframework.stereotype.Repository;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.repository.abstracts.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger log = Logger.getLogger(UserRepositoryImpl.class.getName());

    List<User> users = new ArrayList<>();

    @Override
    public synchronized User save(User user) {
        if (!users.isEmpty()) {
            user.setId(users.get(users.size() - 1).getId() + 1);
        } else {
            user.setId(1l);
        }
        users.add(user);
        return user;
    }

    @Override
    public User find(UserCredential creds) {
        return users.stream()
                .filter(e ->
                        e.getUsername().equals(creds.getUsername())
                                && e.getPassword().equals(creds.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
*/
