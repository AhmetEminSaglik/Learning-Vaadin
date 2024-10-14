package viewmodel.home.payment.form;

import com.vaadin.flow.component.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPageURL;
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

    public void processBtnGiveOrder() {
        if (isFilledAllData()) {
            navigateToPaymentPageWithThread();
        } else {
            String msg = "Please Fill All Data";
            CustomNotification.show(msg);

        }
    }

    private void savePayment() {

    }

    private void navigateToPaymentPageWithThread() {
        String msg = "Successfully ordered.";
        CustomNotification.show(msg);
        msg = "Directing to Payment Page";
        CustomNotification.show(msg);

        UI.getCurrent().access(() -> {
            UI.getCurrent().navigate(EnumPageURL.PAYMENT.getUrl());
            log.info("Payment Page'e geçiş yapıldı");
        });

    }

    private boolean isFilledAllData() {
        try {
            if (!isDataValid(view.getTxtFieldCreditCardOwnerName().getValue().toString())
                    || !isDataValid(view.getNumFieldCreditCardNo().getValue().toString())
                    || !isDataValid(view.getNumFieldPhoneNo().getValue().toString())
            )
                return false;

        } catch (NullPointerException e) {
            return false;
        }
        return true;

    }

    private boolean isDataValid(String value) {
        if (value != null && !value.isBlank()) {
            return true;
        }
        return false;
    }
}
