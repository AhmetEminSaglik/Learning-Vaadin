package org.vaadin.aes.view.home.concretes.payment;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.view.home.concretes.PaymentMethodView;
import viewmodel.home.payment.form.PaymentBottomViewModel;

import java.util.List;
import java.util.logging.Logger;


public class PaymentBottomView extends HorizontalLayout {
    private static final Logger log = Logger.getLogger(PaymentBottomView.class.getName());

    private HtmlComponent totalPrice = new H3();
    private final PaymentMethodView paymentMethodView;
    private final Button btnPay = new Button("Give Order");
    private final PaymentBottomViewModel viewModel;
    private final List<OrderConcept> orderConceptList;


    public PaymentBottomView(PaymentMethodView paymentMethodView, List<OrderConcept> orderConceptList) {
        this.paymentMethodView = paymentMethodView;
        this.orderConceptList = orderConceptList;
        viewModel = new PaymentBottomViewModel(this, paymentMethodView);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        viewModel.calculateTotalPrice();
        viewModel.addClickListenerBtnPay(btnPay);
        add(totalPrice);
        add(btnPay);
    }

    public List<OrderConcept> getOrderConceptList() {
        return orderConceptList;
    }

    public HtmlComponent getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(HtmlComponent totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethodView getPaymentMethodView() {
        return paymentMethodView;
    }
}
