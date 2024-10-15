package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.view.core.CustomHtmlComponents;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.view.home.concretes.address.AddressFormView;
import org.vaadin.aes.view.home.concretes.payment.MyOrderView;
import org.vaadin.aes.view.home.concretes.payment.PaymentBottomView;
import org.vaadin.aes.view.home.concretes.payment.method.PaymentMethodFormView;
import viewmodel.home.PaymentMethodViewModel;

import java.util.List;
import java.util.logging.Logger;

@Route("payment-method")
@PageTitle("Payment Method Page")
public class PaymentMethodView extends AbstractLayoutView {

//    private final PaymentMethodViewModel viewModel;

    private final HorizontalLayout horizontalLayoutBody = new HorizontalLayout();

    private List<OrderConcept> orderConceptList;
    private final PaymentMethodFormView paymentMethodFormView = new PaymentMethodFormView();
    private final MyOrderView myOrderView;
    private final PaymentBottomView paymentBottomView;
    private final AddressFormView addressFormView = new AddressFormView();
    private static final Logger log = Logger.getLogger(PaymentMethodView.class.getName());

    public PaymentMethodView() {
        super(EnumPageURL.PAYMENT_METHOD);
        this.orderConceptList = (List<OrderConcept>) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName());
//        this.viewModel = new PaymentMethodViewModel();

        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);

        myOrderView = new MyOrderView(orderConceptList);
        horizontalLayoutBody.setSizeFull();
        horizontalLayoutBody.setAlignItems(Alignment.CENTER);
        horizontalLayoutBody.add(paymentMethodFormView);
        horizontalLayoutBody.add(myOrderView);
        horizontalLayoutBody.add(addressFormView);
//        btnPay.setWidth("350px");

        paymentBottomView = new PaymentBottomView(this, orderConceptList);
        paymentBottomView.setSizeFull();

        Div bottomLayoutFrameDiv = CustomHtmlComponents.DivUtil.getDivPercent("red", 20, 30);
        bottomLayoutFrameDiv.add(paymentBottomView);

        getBody().add(horizontalLayoutBody);
        getBody().add(bottomLayoutFrameDiv);
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

}
