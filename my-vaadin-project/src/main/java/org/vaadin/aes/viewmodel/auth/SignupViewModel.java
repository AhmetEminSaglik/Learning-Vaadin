package org.vaadin.aes.viewmodel.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.service.abstracts.user.AuthenticationService;

import java.util.logging.Logger;

@Component
public class SignupViewModel {
    private static final Logger log = Logger.getLogger(SignupViewModel.class.getName());
    private final AuthenticationService authService;
    private User user;

    @Autowired
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
