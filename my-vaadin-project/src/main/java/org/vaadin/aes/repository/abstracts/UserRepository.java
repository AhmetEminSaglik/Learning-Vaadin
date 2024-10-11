package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;

public interface UserRepository extends JpaRepository<User, Long> {
    //public interface UserRepository {
//    User save(User user);
    User findUserByUsernameAndPassword(String username, String password);
}
