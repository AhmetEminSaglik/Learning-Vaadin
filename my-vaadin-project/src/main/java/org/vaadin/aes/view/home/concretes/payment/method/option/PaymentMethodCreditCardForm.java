package org.vaadin.aes.view.home.concretes.payment.method.option;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.view.home.concretes.payment.form.CreditCardFormView;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

@Component
@UIScope
public class PaymentMethodCreditCardForm extends Div implements OrderPurchaseValidator {
    private CreditCardFormView creditCardFormView;// = new CreditCardFormView();

    @Autowired
    public PaymentMethodCreditCardForm(CreditCardFormView creditCardFormView) {
        this.creditCardFormView = creditCardFormView;
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

