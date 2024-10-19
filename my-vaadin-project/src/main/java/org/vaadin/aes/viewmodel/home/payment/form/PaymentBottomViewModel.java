package org.vaadin.aes.viewmodel.home.payment.form;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.*;
import org.vaadin.aes.view.core.CashFormatUtil;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import org.vaadin.aes.view.home.concretes.PaymentMethodView;
import org.vaadin.aes.view.home.concretes.payment.PaymentBottomView;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

import java.util.logging.Logger;

@Component
public class PaymentBottomViewModel implements OrderPurchaseValidator {
    //    private final PaymentBottomView view;
    private static final Logger log = Logger.getLogger(PaymentBottomViewModel.class.getName());

    private PaymentMethodView paymentMethodView;

    public PaymentMethodView getPaymentMethodView() {
        return paymentMethodView;
    }

    public void setPaymentMethodView(PaymentMethodView paymentMethodView) {
        this.paymentMethodView = paymentMethodView;
    }

    public void calculateTotalPrice(PaymentBottomView view) {
        double total = paymentMethodView.getOrderConceptList()
                .stream()
                .mapToDouble(e -> e.getMeal().getPrice() * e.getQuantity())
                .sum();
//        view.getTotalPrice().setTitle(CashFormatUtil.convertTL(total));
        view.setTotalPrice(CashFormatUtil.convertTL(total));
    }

    public void addClickListenerBtnPay(PaymentBottomView view, Button btnPay) {
//        btnPay.getgetListeners(ClickEvent.class).forEach(btnPay::removeListener);
        btnPay.addClickListener(e -> {
            if (isValid()) {
                log.info("btnPay --> addClickListenerBtnPay: worked: ");
                String msg = "Successfully ordered.";
                CustomNotification.showShort(msg);

                Address address = createAddress(paymentMethodView);
                Order order = createOrder(address);
                Payment payment = createPayment(view, order);
                log.info("Order conceptList: BEFORE CLEAR " + order.getOrderConcepts());
                UI.getCurrent().getSession().setAttribute(EnumSessionData.ORDER.getName(), order);
                UI.getCurrent().getSession().setAttribute(EnumSessionData.PAYMENT.getName(), payment);

//                paymentMethodView.clearOrderConceptList();
//                log.info("Order conceptList: AFTER CLEAR "+order.getOrderConcepts());
//                log.info("Order conceptList: AFTER CLEAR  --> getAttribute  : "+UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName()));
                log.info("\nUI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName(), order)--> : \n" + UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName()));
                UI.getCurrent().navigate(EnumPageURL.ONLINE_PURCHASE.getUrl());
            }
        });
    }

    private Payment createPayment(PaymentBottomView view, Order order) {
        Payment payment = new Payment();
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(view.getPaymentMethodView().getPaymentMethodFormView().getSelectedMethod().getName());
        payment.setPaymentMethod(paymentMethod);
        payment.setOrder(order);
        return payment;
    }


    private Address createAddress(PaymentMethodView view) {
        Address address = new Address();
        address.setStreet(view.getAddressFormView().getStreet());
        address.setCity(view.getAddressFormView().getCity());
        return address;
    }

    private Order createOrder(Address address) {
        Order order = new Order();
        order.setOrderConcepts(paymentMethodView.getOrderConceptList());
        log.info("paymentMethodView OrderConceptList: " + paymentMethodView.getOrderConceptList());
        order.setUser((User) UI.getCurrent().getSession().getAttribute(EnumSessionData.USER_DATA.getName()));
        order.setAddress(address);
        return order;
    }

    @Override
    public boolean isValid() {
        return paymentMethodView.isValid();
    }
}
