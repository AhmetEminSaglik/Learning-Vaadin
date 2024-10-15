package org.vaadin.aes.view.home.concretes.address;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Component;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import viewmodel.home.service.OrderPurchaseValidator;
//import viewmodel.home.address.AddressFromViewModel;

import java.util.logging.Logger;

public class AddressFormView extends VerticalLayout implements OrderPurchaseValidator {

    private static final Logger log = Logger.getLogger(AddressFormView.class.getName());
//    private final AddressFromViewModel viewModel = new AddressFromViewModel(this);

    private final HtmlComponent formTitle = new H2("Address Form");
    private final HtmlComponent formTitleLine = new Hr();
    private final TextField txtFieldStreet = new TextField();
    private final TextField txtFieldCity = new TextField();

    public AddressFormView() {

        setHeightFull();
        setWidth("30%");
        txtFieldStreet.setLabel("Street");
        txtFieldStreet.setRequiredIndicatorVisible(true);
        txtFieldStreet.setErrorMessage("This field is required");

        txtFieldCity.setLabel("City");
        txtFieldCity.setRequiredIndicatorVisible(true);
        txtFieldCity.setErrorMessage("This field is required");

        add(formTitle, formTitleLine, txtFieldStreet, txtFieldCity);
    }

    public String getStreet() {
        return txtFieldStreet.getValue();
    }

    public String getCity() {
        return txtFieldCity.getValue();
    }

    private boolean isTxtFieldValueValid(TextField textField) {
        String val = textField.getValue();
        if (val.isBlank()) {
            CustomNotification.show("Please fill "+textField.getLabel()+" in Address Form");
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid() {
        return isAllDataValid();
    }

    private boolean isAllDataValid() {
        if (isTxtFieldValueValid(txtFieldStreet)
                && isTxtFieldValueValid(txtFieldCity)) {
            return true;
        }
        return false;
    }
}
