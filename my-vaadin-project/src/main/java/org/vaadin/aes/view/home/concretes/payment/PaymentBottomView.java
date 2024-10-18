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

    @Autowired
    public PaymentBottomView(PaymentBottomViewModel viewModel) {
        this.viewModel = viewModel;
//        viewModel = new PaymentBottomViewModel(this, paymentMethodView);

        addCssForView();

//        viewModel.setView(this);
        add(totalPrice);
        add(btnPay);
    }

    private void addCssForView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
//    public  void  setOrderConceptList(List<OrderConcept> orderConceptList){
//        this.orderConceptList=orderConceptList;
//    }
//
//    public List<OrderConcept> getOrderConceptList() {
//        return orderConceptList;
//    }

    public HtmlComponent getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String title) {
        totalPrice.getElement().setText(title);
    }

    public PaymentMethodView getPaymentMethodView() {
        return paymentMethodView;
    }

    public void  setPaymentMethodView(PaymentMethodView paymentMethodView) {
        this.paymentMethodView = paymentMethodView;
        viewModel.setPaymentMethodView(paymentMethodView);
        viewModel.calculateTotalPrice(this);
        viewModel.addClickListenerBtnPay(this, btnPay);
    }

    @Override
    public boolean isValid() {
        if (paymentMethodView == null) {
            log.severe("paymentMethodView is null : " + paymentMethodView);
        }
        return paymentMethodView.isValid();
    }
}
