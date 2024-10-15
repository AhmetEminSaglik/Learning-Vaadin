package viewmodel.home.payment.form;

import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.view.home.concretes.payment.method.PaymentMethodFormView;

import java.util.logging.Logger;

public class PaymentMethodFormViewModel {
    private final PaymentMethodFormView view;

    private static final Logger log = Logger.getLogger(PaymentMethodFormViewModel.class.getName());

    public PaymentMethodFormViewModel(PaymentMethodFormView view) {
        this.view = view;
    }


    public void activateCashPaymentMethod() {
        view.getPaymentCreditCardForm().deactivate();
        view.getPaymentCashForm().activate();
        view.setSelectedMethod(EnumPaymentMethod.CASH);
        view.setOrderPurchaseValidator(view.getPaymentCashForm());
    }

    public void activateCreditCardPaymentMethod() {
        view.getPaymentCreditCardForm().activate();
        view.getPaymentCashForm().deactivate();
        view.setSelectedMethod(EnumPaymentMethod.CARD);
        view.setOrderPurchaseValidator(view.getPaymentCreditCardForm());
    }

    public void updateSelectedOptionVisibility() {
        switch (view.getSelectedMethod()) {
            case CARD:
                activateCreditCardPaymentMethod();
                break;
            case CASH:
                activateCashPaymentMethod();
                break;
            default:
                log.info("Invalid Choose : " + view.getSelectedMethod());
        }
    }
}
