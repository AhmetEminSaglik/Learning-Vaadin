package org.vaadin.aes.repository.abstracts;

import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;

//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository {
    User save(User user);
    User find(UserCredential creds);
}
