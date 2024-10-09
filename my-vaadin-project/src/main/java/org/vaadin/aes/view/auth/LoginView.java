package org.vaadin.aes.view.auth;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.service.user.AuthenticationService;
import viewmodel.auth.LoginViewModel;
import viewmodel.auth.SignupViewModel;

import java.util.stream.Stream;

@Route("login")
@PageTitle("Sign up  | Food App")
public class LoginView extends VerticalLayout {

    private static final Logger log = LoggerFactory.getLogger(LoginView.class);
    private SignupViewModel signupViewModel;
    private static boolean result;
    private Button btnNavigateSignup = new Button("Signup Page");

    private LoginViewModel loginViewModel;
    private TextField txtUsername = new TextField("Username");
    private TextField txtPassword = new TextField("Password");
    private Button btnLogin;
    private H1 userFeedBack= new H1();

    @Autowired
    public LoginView(AuthenticationService authenticationService) {
        loginViewModel = new LoginViewModel(authenticationService, userFeedBack);
        btnNavigateSignup.addClickListener(event -> {
            // Diğer görünümün adı (örneğin, "anotherView" veya "viewName")
            getUI().ifPresent(ui -> ui.navigate("signup"));
        });
        initPage();
    }

    private void initPage() {
        result = true;
        setSizeFull();
        btnLogin = getBtnLogin();
        add(
                btnNavigateSignup,
                txtUsername,
                txtPassword,
                btnLogin);
    }

    private Button getBtnLogin() {
        Button button = new Button("Login");
        button.addClickListener(e -> {
            if (isAllFieldsFilled()) {
                UserCredential cred = convertTypedDataToUserCredModel();
                User user = loginViewModel.login(cred);
                btnLogin.addClickListener(event -> {
                    getUI().ifPresent(ui -> ui.navigate("home"));
                });
            }
            resetAllTextFields();
        });
        return button;
    }

    private boolean isAllFieldsFilled() {
        getStreamOfTxtFields().forEach(e -> {
            if (e.getValue().isBlank()) {
                e.setErrorMessage("Please fill the required fields :" + e.getTitle());
                result = false;
            }
        });
        return result;
    }

    private UserCredential convertTypedDataToUserCredModel() {
        UserCredential cred = new UserCredential();
        cred.setUsername(txtUsername.getValue());
        cred.setPassword(txtPassword.getValue());
        return cred;
    }

    private void resetAllTextFields() {
        if (result) {
            getStreamOfTxtFields()
                    .forEach(HasValue::clear);
        }
    }

    private Stream<TextField> getStreamOfTxtFields() {
        return Stream.of(txtUsername, txtPassword);
    }
}
