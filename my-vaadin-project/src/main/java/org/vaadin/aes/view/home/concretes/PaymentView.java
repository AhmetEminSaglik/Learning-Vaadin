package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.router.Route;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;

@Route("payment")
public class PaymentView extends AbstractLayoutView {
    public PaymentView() {
        super(EnumPageURL.PAYMENT);
    }
}
