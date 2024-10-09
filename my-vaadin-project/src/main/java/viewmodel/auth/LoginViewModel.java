package viewmodel.auth;

import com.vaadin.flow.component.html.H1;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.service.user.AuthenticationService;

import java.util.logging.Logger;

public class LoginViewModel {
    private static final Logger log = Logger.getLogger(LoginViewModel.class.getName());

    private AuthenticationService authService;
    private User user;
    private H1 userFeedBack;

    public LoginViewModel(AuthenticationService authService, H1 userFeedBack) {
        this.authService = authService;
        this.userFeedBack = userFeedBack;
    }

    public User login(UserCredential creds) {
        user = authService.login(creds);
        if (user == null) {
            userFeedBack.setText("Username or password is invalid");
        }
        return user;

    }
}
