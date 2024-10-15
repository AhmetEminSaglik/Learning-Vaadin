package org.vaadin.aes.view.home.concretes.payment.method.option;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import org.vaadin.aes.view.home.concretes.payment.form.CreditCardFormView;
import viewmodel.home.service.OrderPurchaseValidator;

public class PaymentMethodCreditCardForm extends Div implements OrderPurchaseValidator {
    private CreditCardFormView creditCardFormView = new CreditCardFormView();

    public PaymentMethodCreditCardForm() {
        setWidthFull();
        setHeight("50%");

        setClassName("design-card");
        add(new H2("Pay with Credit Card"));
        add(new Hr());
        add(creditCardFormView);
    }

    public void activate() {
        setVisible(true);
    }

    public void deactivate() {
        setVisible(false);
    }

    @Override
    public boolean isValid() {
        return creditCardFormView.isValid();
    }
}

