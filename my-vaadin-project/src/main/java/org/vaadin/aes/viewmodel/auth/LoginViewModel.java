package org.vaadin.aes.viewmodel.auth;

import com.vaadin.flow.component.HtmlContainer;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.service.abstracts.user.AuthenticationService;

import java.util.logging.Logger;

public class LoginViewModel {
    private static final Logger log = Logger.getLogger(LoginViewModel.class.getName());

    private AuthenticationService authService;
    private User user;
    private HtmlContainer userFeedBack;

    public LoginViewModel(AuthenticationService authService, HtmlContainer userFeedBack) {
        this.authService = authService;
        this.userFeedBack = userFeedBack;
    }

    public User login(UserCredential creds) {
        user = authService.login(creds);
        if (user == null) {
            userFeedBack.setText("Username or password is invalid");
            log.info("User is not found. and userFeedback is updated");
        }
        return user;

    }
}
