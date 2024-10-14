package org.vaadin.aes.view.auth;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.service.abstracts.user.AuthenticationService;
import org.vaadin.aes.auth.SignupViewModel;

import java.util.stream.Stream;


@Route("signup")
@PageTitle("Sign up  | Food App")
public class SignupView extends VerticalLayout {

    private SignupViewModel signupViewModel;
    //    private static boolean result;
    //    String txtDemo = UI.getCurrent().getTranslation("farewell");
//    private TextField demoText = new TextField(txtDemo);
//    private AuthenticationService authenticationService;
    private Button btnNavigateLogin = new Button("Login Page");

//    private Button btnSwitchToEn = new Button("English", e -> UI.getCurrent().setLocale(new Locale("en")));
//    private Button btnSwitchToTr = new Button("Türkçe", e -> UI.getCurrent().setLocale(new Locale("tr")));

    private TextField txtFirstName = new TextField("First Name");
    private TextField txtLastName = new TextField("Lastname");

    private TextField txtEmail = new TextField("Email");
    private TextField txtPhone = new TextField("Phone");

    private TextField txtUsername = new TextField("Username");
    private TextField txtPassword = new TextField("Password");
    private TextField txtConfirmPassword = new TextField("Confirm Password");

    private HtmlContainer feedBackToUser = new H3();

    @Autowired
    public SignupView(AuthenticationService authenticationService) {
//        txtFirstName = CurrentLanguage.setCurrentLanguageToTxtField(txtFirstName, EnumI18NLanguageSupport.USER_FIRST_NAME);
//        this.authenticationService = authenticationService;
        this.signupViewModel = new SignupViewModel(authenticationService);
        btnNavigateLogin.addClickListener(event -> {
            // Diğer görünümün adı (örneğin, "anotherView" veya "viewName")
            getUI().ifPresent(ui -> ui.navigate("login"));
        });
        initPage();
        /*btnSwitchToTr.addClickListener((e) -> {
            UI.getCurrent().setLocale(new Locale("tr"));
            updateDemoText(); // Güncelleme fonksiyonunu çağır
        });

        btnSwitchToEn.addClickListener((e) -> {
            UI.getCurrent().setLocale(new Locale("en"));
            updateDemoText(); // Güncelleme fonksiyonunu çağır
        });*/
    }

    private void initPage() {
//        result = true;
        setRequiredToAllTxtFields();
        setSizeFull();
        Button btnSave = getBtnSaveUser();
        add(
                btnNavigateLogin,
//                demoText,
//                btnSwitchToEn,
//                btnSwitchToTr,
                txtFirstName,
                txtLastName,
                txtEmail,
                txtPhone,
                txtUsername,
                txtPassword,
                txtConfirmPassword,
                btnSave,
                feedBackToUser);

    }

    private void setRequiredToAllTxtFields() {
        getStreamOfTxtFields().forEach(e -> e.setRequired(true));
    }

    private Button getBtnSaveUser() {
        Button button = new Button("Save");
        button.addClickListener(e -> {
            boolean result = true;
            if (!isAllFieldsFilled()) {
                feedBackToUser.setText("Please fill all fields");
            } else {
//                if (feedBackToUser != null) {
//                    remove(feedBackToUser);
//                }
                if (!isPasswordMatches()) {
                    feedBackToUser.setText("Passwords do not match");
                    return;
                }
                User user = convertTypedDataToUserModel();
                result = signupViewModel.signUp(user);
                if (result) {
                    feedBackToUser.setText("User Saved successfully. You can login now.");

                } else {
                    feedBackToUser.setText("Error occurred. User is not saved.");
                }
                resetAllTextFields();
            }
        });

        return button;
    }

    private boolean isAllFieldsFilled() {
        /*getStreamOfTxtFields().forEach(e -> {
            if (e.getValue().isBlank()) {
//                e.setErrorMessage("Please fill the required fields :" + e.getTitle());
                result = false;
            }
        });*/

        boolean anyBlankField = getStreamOfTxtFields()
                .map(e -> e.getValue().isBlank())
                .anyMatch(isBlank -> isBlank);

        return !anyBlankField;
    }

    private boolean isPasswordMatches() {
        boolean result = txtPassword.getValue().equals(txtConfirmPassword.getValue());
        if (!result) {
            txtConfirmPassword.setErrorMessage("Passwords are not matched.");
        }
        return result;
    }

    private User convertTypedDataToUserModel() {
        User user = new User();
        user.setFirstName(txtFirstName.getValue());
        user.setLastName(txtLastName.getValue());
        user.setUsername(txtUsername.getValue());
        user.setPassword(txtPassword.getValue());
        user.setEmail(txtEmail.getValue());
        user.setPhoneNo(txtPhone.getValue());
        return user;
    }

    private void resetAllTextFields() {
//        if (result) {
        getStreamOfTxtFields()
                .forEach(HasValue::clear);
//        }
    }

    private Stream<TextField> getStreamOfTxtFields() {
        return Stream.of(txtFirstName, txtLastName, txtEmail, txtPhone, txtUsername, txtPassword, txtConfirmPassword);
    }
//    private void updateDemoText() {
//        txtDemo = UI.getCurrent().getTranslation("farewell");
//        demoText.setLabel(txtDemo); // Label'ı güncelle
//    }
}