package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.view.core.CustomHtmlComponents;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.view.home.concretes.address.AddressFormView;
import org.vaadin.aes.view.home.concretes.payment.MyOrderView;
import org.vaadin.aes.view.home.concretes.payment.PaymentBottomView;
import org.vaadin.aes.view.home.concretes.payment.method.PaymentMethodFormView;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

import java.util.List;
import java.util.logging.Logger;

@Route("payment-method")
@PageTitle("Payment Method Page")
public class PaymentMethodView extends AbstractLayoutView implements OrderPurchaseValidator {

//    private final PaymentMethodViewModel viewModel;

    private final HorizontalLayout horizontalLayoutBody = new HorizontalLayout();

    private List<OrderConcept> orderConceptList;
    private final PaymentMethodFormView paymentMethodFormView;// = new PaymentMethodFormView();
    private final MyOrderView myOrderView;
    private final PaymentBottomView paymentBottomView;
    private final AddressFormView addressFormView;
    private OrderPurchaseValidator validatorPaymentMethodForm;
    private OrderPurchaseValidator validatorAddressForm;
    private static final Logger log = Logger.getLogger(PaymentMethodView.class.getName());

    @Autowired
    public PaymentMethodView(PaymentMethodFormView paymentMethodFormView, PaymentBottomView paymentBottomView, AddressFormView addressFormView) {
        super(EnumPageURL.PAYMENT_METHOD);
        this.paymentMethodFormView = paymentMethodFormView;
        this.orderConceptList = (List<OrderConcept>) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName());
        this.paymentBottomView = paymentBottomView;
        this.addressFormView = addressFormView;
        setValidators();

        log.info(">>> orderConceptList: "+orderConceptList);

        myOrderView = new MyOrderView(orderConceptList);
        paymentBottomView.setPaymentMethodView(this);
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

//        horizontalLayoutBody.setHeight("85%");
//        horizontalLayoutBody.setWidth("100%");
        horizontalLayoutBody.setSizeFull();
        horizontalLayoutBody.setAlignItems(Alignment.CENTER);
        horizontalLayoutBody.add(paymentMethodFormView);
        horizontalLayoutBody.add(myOrderView);
        horizontalLayoutBody.add(addressFormView);
//        btnPay.setWidth("350px");

//        paymentBottomView = new PaymentBottomView(this, orderConceptList);
//        paymentBottomView.setHeight("15%");
//        paymentBottomView.setWidth("100%");
        paymentBottomView.setSizeFull();

        Div bottomLayoutFrameDiv = CustomHtmlComponents.DivUtil.getDivPercent("red", 20, 30);
        bottomLayoutFrameDiv.add(paymentBottomView);

        getBody().add(horizontalLayoutBody);
        getBody().add(bottomLayoutFrameDiv);
    }

    private void setValidators() {
        validatorAddressForm = addressFormView;
        validatorPaymentMethodForm = paymentMethodFormView;
    }

//    public PaymentMethodViewModel getViewModel() {
//        return viewModel;
//    }

    public PaymentMethodFormView getPaymentMethodFormView() {
        return paymentMethodFormView;
    }

    public AddressFormView getAddressFormView() {
        return addressFormView;
    }

    public List<OrderConcept> getOrderConceptList() {
        return orderConceptList;
    }

    @Override
    public boolean isValid() {
        return (validatorPaymentMethodForm.isValid() && validatorAddressForm.isValid());
    }
}
