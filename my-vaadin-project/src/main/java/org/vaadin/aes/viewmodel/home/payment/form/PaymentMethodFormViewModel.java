package org.vaadin.aes.viewmodel.home.payment.form;

import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.view.home.concretes.payment.method.PaymentMethodFormView;

import java.util.logging.Logger;

@Component
public class PaymentMethodFormViewModel {
    private static final Logger log = Logger.getLogger(PaymentMethodFormViewModel.class.getName());

    public PaymentMethodFormViewModel() {
    }


    public void activateCashPaymentMethod(PaymentMethodFormView view) {
        view.getPaymentCreditCardForm().deactivate();
        view.getPaymentCashForm().activate();
        view.setSelectedMethod(EnumPaymentMethod.CASH);
        view.setOrderPurchaseValidator(view.getPaymentCashForm());
    }

    public void activateCreditCardPaymentMethod(PaymentMethodFormView view) {
        view.getPaymentCreditCardForm().activate();
        view.getPaymentCashForm().deactivate();
        view.setSelectedMethod(EnumPaymentMethod.CARD);
        view.setOrderPurchaseValidator(view.getPaymentCreditCardForm());
    }

    public void updateSelectedOptionVisibility(PaymentMethodFormView view) {
        switch (view.getSelectedMethod()) {
            case CARD:
                activateCreditCardPaymentMethod(view);
                break;
            case CASH:
                activateCashPaymentMethod(view);
                break;
            default:
                log.info("Invalid Choose : " + view.getSelectedMethod());
        }
    }
}
