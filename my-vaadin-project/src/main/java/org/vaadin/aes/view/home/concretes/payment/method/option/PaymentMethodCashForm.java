package org.vaadin.aes.view.home.concretes.payment.method.option;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import org.vaadin.aes.view.home.concretes.PaymentView;


public class PaymentMethodCashForm extends Div {

    private Button btnPay = new Button("Give Order");

    public PaymentMethodCashForm() {
        setClassName("design-card");
        add(new H2("Pay with Cash"));

        btnPay.addClickListener(e -> {
            showUpSuccessfullyNotification();
            UI.getCurrent().navigate(PaymentView.class);
        });

        add(btnPay);
    }

    public void activate() {
        setVisible(true);
    }

    public void deactivate() {
        setVisible(false);
    }

    private void showUpSuccessfullyNotification() {
        String msg = "Order has been successfully created.";
        CustomNotification.show(msg);
    }
}

