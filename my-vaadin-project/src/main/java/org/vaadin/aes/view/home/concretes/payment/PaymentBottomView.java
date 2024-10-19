package org.vaadin.aes.view.home.concretes.payment;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.view.home.concretes.PaymentMethodView;
import org.vaadin.aes.viewmodel.home.payment.form.PaymentBottomViewModel;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

import java.util.logging.Logger;


@Component
public class PaymentBottomView extends HorizontalLayout implements OrderPurchaseValidator {
    private static final Logger log = Logger.getLogger(PaymentBottomView.class.getName());

    private HtmlComponent totalPrice = new H3("Unhanded Total Price");
    private final Button btnPay = new Button("Give Order");
    private final PaymentBottomViewModel viewModel;
    //    private List<OrderConcept> orderConceptList;
    private PaymentMethodView paymentMethodView;
    private boolean isPageCreatedBefore = false;

    @Autowired
    public PaymentBottomView(PaymentBottomViewModel viewModel) {
        this.viewModel = viewModel;
        addCssForView();
        add(totalPrice);
        add(btnPay);
    }

    private void addCssForView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    public HtmlComponent getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String title) {
        totalPrice.getElement().setText(title);
    }

    public PaymentMethodView getPaymentMethodView() {
        return paymentMethodView;
    }

    public void setPaymentMethodView(PaymentMethodView paymentMethodView) {
//            log.info("setPaymentMethodView'e geldi: isPageCreatedBefore="+isPageCreatedBefore);
//        if (!isPageCreatedBefore) {
            isPageCreatedBefore = true;
            this.paymentMethodView = paymentMethodView;
            viewModel.setPaymentMethodView(paymentMethodView);
            viewModel.addClickListenerBtnPay(this, btnPay);
            viewModel.calculateTotalPrice(this);
//        }
    }

    @Override
    public boolean isValid() {
        if (paymentMethodView == null) {
            log.severe("paymentMethodView is null : " + paymentMethodView);
        }
        return paymentMethodView.isValid();
    }
}
