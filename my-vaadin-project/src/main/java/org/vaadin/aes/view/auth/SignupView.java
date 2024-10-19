package org.vaadin.aes.view.auth;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldBase;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumCssClassName;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.viewmodel.auth.SignupViewModel;

import java.util.stream.Stream;

@Route("signup")
@PageTitle("Sign up | Food App")
@UIScope
public class SignupView extends VerticalLayout {

    private final SignupViewModel viewModel;
    //    private Button btnSave;
//    private Button btnNavigateLogin;
    private TextField txtFirstName = new TextField("First Name");
    private TextField txtLastName = new TextField("Last Name");
    private TextField txtEmail = new TextField("Email");
    private TextField txtPhone = new TextField("Phone");
    private TextField txtUsername = new TextField("Username");
    private PasswordField txtPassword = new PasswordField("Password");
    private PasswordField txtConfirmPassword = new PasswordField("Confirm Password");
    private HtmlContainer feedBackToUser = new H3();

    @Autowired
    public SignupView(SignupViewModel viewModel) {
        this.viewModel = viewModel;
//        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
//        createBtnNavigateLogin();
        addCss();
        initPage();
    }

    private Button getBtnNavigateLogin() {
        Button button = new Button("Go to Login Page");
        button.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(EnumPageURL.LOGIN_PAGE.getUrl()));
        });
        button.addClassName(EnumCssClassName.BTN.getName());
        button.addClassName(EnumCssClassName.BTN_PRIMARY.getName());
//        button.addClassName("btn-lg");
//        button.addClassNames("btn", "btn-primary", "btn-lg");
        return button;
    }

    private void initPage() {
//        setHeightFull();
        setHeight("auto ");
//        setHeight("50%");
        add(
                getBtnNavigateLogin(),
                getHorizontalLayout(txtFirstName, txtLastName),
                getHorizontalLayout(txtEmail, txtPhone),
                getHorizontalLayout(txtUsername),
                getHorizontalLayout(txtPassword, txtConfirmPassword),
                getBtnSaveUser(),
                feedBackToUser
        );
    }

    private HorizontalLayout getHorizontalLayout(TextFieldBase txtField1, TextFieldBase txtField2) {
        HorizontalLayout horizontalLayout = getDesignedHorizontalLayout();
        horizontalLayout.add(txtField1);
        horizontalLayout.add(txtField2);
        return horizontalLayout;
    }

    private HorizontalLayout getHorizontalLayout(TextFieldBase txtField) {
        HorizontalLayout horizontalLayout = getDesignedHorizontalLayout();
        horizontalLayout.add(txtField);
        return horizontalLayout;
    }

    private HorizontalLayout getDesignedHorizontalLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setHeight("auto");
        horizontalLayout.setWidth("50%");

        return horizontalLayout;
    }


    private Button getBtnSaveUser() {
        Button button = new Button("Save");

        button.addClassName(EnumCssClassName.BTN.getName());
        button.addClassName(EnumCssClassName.BTN_PRIMARY.getName());
//        button.addClassName("large-button");
        button.addClassName(EnumCssClassName.BTN.getName());
        button.addClassName(EnumCssClassName.BTN_PRIMARY.getName());
        button.addClickListener(e -> {
            boolean result = true;
            if (!isAllFieldsFilled()) {
                feedBackToUser.setText("Please fill all fields");
            } else {
                if (!isPasswordMatches()) {
                    feedBackToUser.setText("Passwords do not match");
                    return;
                }
                User user = convertTypedDataToUserModel();
                result = viewModel.signUp(user);
                if (result) {
                    feedBackToUser.setText("User saved successfully. You can log in now.");
                } else {
                    //todo here must be return DataResult not only user or null. So this is temporary solution
                    feedBackToUser.setText("User is not saved. Please pick up another username");
                }
                resetAllTextFields();
            }
        });

        return button;
    }

    private boolean isAllFieldsFilled() {
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
        getStreamOfTxtFields().forEach(HasValue::clear);
    }

    private Stream<TextFieldBase<? extends TextFieldBase<?, ?>, String>> getStreamOfTxtFields() {
        return Stream.of(txtFirstName, txtLastName, txtEmail, txtPhone, txtUsername, txtPassword, txtConfirmPassword);
    }

    private void addCss() {
        getStreamOfTxtFields().forEach(field -> {
//            field.setSizeFull();
            field.setSizeFull();
            field.addClassName("input-lg");
            field.addClassName("forrm-group-lg");
//            field.addClassNames("form-control input-lg"); // Bootstrap sınıflarını ekle
        });
//        txtPassword.addClassNames(/*"form-control",*/ "mb-3");
//        txtConfirmPassword.addClassNames("form-control", "mb-3");
    }

    /*private void setRequiredToAllTxtFields() {
        getStreamOfTxtFields().forEach(e -> e.setRequired(true));
    }*/
}
