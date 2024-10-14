package org.vaadin.aes.view.auth;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.concrete.UserCredential;
import org.vaadin.aes.model.dto.UserDataDto;
import org.vaadin.aes.model.mapper.UserMapper;
import org.vaadin.aes.service.abstracts.user.AuthenticationService;
import org.vaadin.aes.auth.LoginViewModel;
import org.vaadin.aes.auth.SignupViewModel;

import java.util.stream.Stream;

@Route("login")
@PageTitle("Login Page")
public class LoginView extends VerticalLayout {

    private static final Logger log = LoggerFactory.getLogger(LoginView.class);
    private SignupViewModel signupViewModel;
    private static boolean result;
    private Button btnNavigateSignup = new Button("Signup Page");

    private LoginViewModel loginViewModel;
    private TextField txtUsername = new TextField("Username");
    private TextField txtPassword = new TextField("Password");
    private Button btnLogin;
    private HtmlContainer userFeedBack = new H3();

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
                btnLogin,
                userFeedBack);
    }

    private Button getBtnLogin() {
        Button button = new Button("Login");
        button.addClickListener(e -> {
            userFeedBack.setText("");
            if (isAllFieldsFilled()) {
                UserCredential cred = convertTypedDataToUserCredModel();
                User user = loginViewModel.login(cred);
                if (user != null) {
                    UserDataDto userData = UserMapper.userToUserDataDto(user);

                    VaadinSession.getCurrent().setAttribute(EnumDTO.USER_DATA.getName(), userData);
                    log.info("Loggin successfully: saved user data in " + EnumDTO.USER_DATA.getName() + " as : " + userData);
                    getUI().ifPresent(ui -> ui.navigate(EnumPageURL.FOOD_PAGE.getUrl()));
                }
            }
            resetAllTextFields();
        });
        return button;
    }

    private boolean isAllFieldsFilled() {
        getStreamOfTxtFields().forEach(e -> {
            if (e.getValue().isBlank()) {
//                e.setErrorMessage("Please fill the required fields :" + e.getTitle());
                userFeedBack.setText("Please fill the required fields :");
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
