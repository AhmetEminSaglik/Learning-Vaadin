package org.vaadin.aes.view.home.concretes.payment.form;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.viewmodel.home.payment.form.CreditCardFormViewModel;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

@Component
public class CreditCardFormView extends VerticalLayout implements OrderPurchaseValidator {

    private final CreditCardFormViewModel viewModel;
    private final TextField txtFieldCreditCardOwnerName = new TextField();
    private final TextField txtFieldCreditCardNo = new TextField();
    private final TextField txtFieldPhoneNo = new TextField();
//    private final Button btnPay = new Button("Give Order");

    @Autowired
    public CreditCardFormView(CreditCardFormViewModel viewModel) {
        this.viewModel = viewModel;

        txtFieldCreditCardOwnerName.setLabel("Credit Card Owner Name");
        txtFieldCreditCardOwnerName.setRequiredIndicatorVisible(true);
        txtFieldCreditCardOwnerName.setErrorMessage("This field is required");

        txtFieldCreditCardNo.setLabel("Credit Card No");
        txtFieldCreditCardNo.setRequiredIndicatorVisible(true);
        txtFieldCreditCardNo.setErrorMessage("This field is required");

        txtFieldPhoneNo.setLabel("Phone number");
        txtFieldPhoneNo.setRequiredIndicatorVisible(true);
        txtFieldPhoneNo.setHelperText("Include city  and area prefixes");


//        btnPay.addClickListener(e -> {
//            viewModel.processBtnGiveOrder();
//        });

        setSizeFull();

        add(txtFieldCreditCardOwnerName, txtFieldCreditCardNo, txtFieldPhoneNo);
    }

    public TextField getTxtFieldCreditCardOwnerName() {
        return txtFieldCreditCardOwnerName;
    }

    public TextField getTxtFieldCreditCardNo() {
        return txtFieldCreditCardNo;
    }

    public TextField getTxtFieldPhoneNo() {
        return txtFieldPhoneNo;
    }

    @Override
    public boolean isValid() {
        return viewModel.isFilledAllData(this);
    }
}
