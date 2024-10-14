package org.vaadin.aes.view.home.concretes.payment.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import viewmodel.home.payment.form.CreditCardFormViewModel;


public class CreditCardFormView extends VerticalLayout {

    private final CreditCardFormViewModel viewModel = new CreditCardFormViewModel(this);
    private final TextField txtFieldCreditCardOwnerName = new TextField();
    private final NumberField numFieldCreditCardNo = new NumberField();
    private final NumberField numFieldPhoneNo = new NumberField();
    private final Button btnPay = new Button("Give Order");

    public CreditCardFormView() {

        txtFieldCreditCardOwnerName.setLabel("Credit Card Owner Name");
        txtFieldCreditCardOwnerName.setRequiredIndicatorVisible(true);
        txtFieldCreditCardOwnerName.setErrorMessage("This field is required");

        numFieldCreditCardNo.setLabel("Credit Card No");
        numFieldCreditCardNo.setRequiredIndicatorVisible(true);
        numFieldCreditCardNo.setErrorMessage("This field is required");

        numFieldPhoneNo.setLabel("Phone number");
        numFieldPhoneNo.setRequiredIndicatorVisible(true);
        numFieldPhoneNo.setHelperText("Include country and area prefixes");


        btnPay.addClickListener(e -> {
            viewModel.processBtnGiveOrder();
        });

        setSizeFull();

        add(txtFieldCreditCardOwnerName, numFieldCreditCardNo, numFieldPhoneNo, btnPay);
    }

    public TextField getTxtFieldCreditCardOwnerName() {
        return txtFieldCreditCardOwnerName;
    }

    public NumberField getNumFieldCreditCardNo() {
        return numFieldCreditCardNo;
    }

    public NumberField getNumFieldPhoneNo() {
        return numFieldPhoneNo;
    }
}
