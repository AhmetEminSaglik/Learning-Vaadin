package viewmodel.home.payment.form;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.*;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.view.core.CashFormatUtil;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import org.vaadin.aes.view.home.concretes.PaymentMethodView;
import org.vaadin.aes.view.home.concretes.payment.PaymentBottomView;

import java.util.logging.Logger;

public class PaymentBottomViewModel {
    private final PaymentBottomView view;
    private static final Logger log = Logger.getLogger(PaymentBottomViewModel.class.getName());
    private final PaymentMethodView paymentMethodView;

    public PaymentBottomViewModel(PaymentBottomView view, PaymentMethodView paymentMethodView) {
        this.view = view;
        this.paymentMethodView = paymentMethodView;
    }

    public void calculateTotalPrice() {
        double total = view.getOrderConceptList()
                .stream()
                .mapToDouble(e -> e.getMeal().getPrice() * e.getQuantity())
                .sum();
//        view.getTotalPrice().setTitle(CashFormatUtil.convertTL(total));
        view.setTotalPrice(new H3(CashFormatUtil.convertTL(total)));
    }

    public void addClickListenerBtnPay(Button btnPay) {
        btnPay.addClickListener(e -> {
            if (isAllValid()) {

                String msg = "Successfully ordered.";
                CustomNotification.show(msg);

                Address address = createAddress();
                Order order = createOrder(address);
                Payment payment = createPayment(order);

                UI.getCurrent().getSession().setAttribute(EnumSessionData.ORDER.getName(), order);
                UI.getCurrent().getSession().setAttribute(EnumSessionData.PAYMENT.getName(), payment);
                UI.getCurrent().navigate(EnumPageURL.ONLINE_PURCHASE.getUrl());
            }
        });
    }

    private Payment createPayment(Order order) {
        Payment payment = new Payment();
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(view.getPaymentMethodView().getPaymentMethodFormView().getSelectedMethod().getName());
        payment.setPaymentMethod(paymentMethod);
        payment.setOrder(order);
        return payment;
    }


    private Address createAddress() {
        Address address = new Address();
        address.setStreet(view.getPaymentMethodView().getAddressFormView().getStreet());
        address.setCity(view.getPaymentMethodView().getAddressFormView().getCity());
        return address;
    }

    private Order createOrder(Address address) {
        Order order = new Order();
        order.setOrderConcepts(view.getOrderConceptList());
        order.setUser((User) UI.getCurrent().getSession().getAttribute(EnumSessionData.USER_DATA.getName()));
        order.setAddress(address);
        return order;
    }

    private boolean isAllValid() {
        if (paymentMethodView.getPaymentMethodFormView().isValid()
                && paymentMethodView.getAddressFormView().isValid()
        ) {
            return true;
        }
        return false;

    }

}
