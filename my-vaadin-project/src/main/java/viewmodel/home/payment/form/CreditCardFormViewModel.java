package viewmodel.home.payment.form;

import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.service.abstracts.payment.PaymentService;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import org.vaadin.aes.view.home.concretes.payment.form.CreditCardFormView;


@Component
public class CreditCardFormViewModel {
    private static final Logger log = LoggerFactory.getLogger(CreditCardFormViewModel.class);
    private CreditCardFormView view;

    @Autowired
    private PaymentService paymentService;

    public CreditCardFormViewModel(CreditCardFormView view) {
        this.view = view;
    }

    /*public void processBtnGiveOrder() {
        if (isFilledAllData()) {
            navigateToPaymentPageWithThread();
        } else {
            String msg = "Please Fill All Data";
            CustomNotification.showShort(msg);

        }
    }
    private void navigateToPaymentPageWithThread() {
        String msg = "Successfully ordered.";
        CustomNotification.showShort(msg);
        msg = "Directing to Payment Page";
        CustomNotification.showShort(msg);

        UI.getCurrent().access(() -> {
            UI.getCurrent().navigate(EnumPageURL.PAYMENT.getUrl());
            log.info("Payment Page'e geçiş yapıldı");
        });

    }*/

    public boolean isFilledAllData() {
        if (isDataValid(view.getTxtFieldCreditCardOwnerName())
                && isDataValid(view.getTxtFieldCreditCardNo())
                && isDataValid(view.getTxtFieldPhoneNo())
        ) {
            return true;
        }

        return false;

    }

    private boolean isDataValid(NumberField field) {
        try {
            if (field.getValue() <= 100) {
                return true;
            }
        } catch (Exception e) {
            log.info("Error occured: "+e.getMessage());
        }
        CustomNotification.showShort("Please fill " + field.getLabel());
        return false;

    }

    private boolean isDataValid(TextField field) {
        if (field != null
                && !field.getValue().isBlank()) {
            return true;
        }
        CustomNotification.showShort("Please fill " + field.getLabel());
        return false;
    }
}
