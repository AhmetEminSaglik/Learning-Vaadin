package org.vaadin.aes.auth;

import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.service.abstracts.user.AuthenticationService;

import java.util.logging.Logger;

public class SignupViewModel {
    private static final Logger log = Logger.getLogger(SignupViewModel.class.getName());
    private AuthenticationService authService;
    private User user;

    public SignupViewModel(AuthenticationService authService) {
        this.authService = authService;
        this.user = new User();
    }

    public boolean signUp(User user) {
        // İşlem sonucuna göre geri döndür
        log.info("User is retrieved to save:");
        return authService.signUp(user) != null;
    }

//    public void setUserData(String firstName, String lastName, String email, String phone, String username, String password) {
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPhoneNo(phone);
//        user.setUsername(username);
//        user.setPassword(password);
//    }


}
