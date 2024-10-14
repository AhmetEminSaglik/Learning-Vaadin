package org.vaadin.aes.view.home.concretes.payment.method.option;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import org.vaadin.aes.view.home.concretes.payment.form.CreditCardFormView;

public class PaymentMethodCreditCardForm extends Div {
    private CreditCardFormView creditCardFormView = new CreditCardFormView();


    public PaymentMethodCreditCardForm() {
        setWidthFull();
        setHeight("50%");

        setClassName("design-card");
        add(new H2("Pay with Credit Card"));

        add(creditCardFormView);
    }

    public void activate() {
        setVisible(true);
    }

    public void deactivate() {
        setVisible(false);
    }
}

