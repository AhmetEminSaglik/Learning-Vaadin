package org.vaadin.aes.view.home.concretes.onlinepurchasing;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.Payment;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.viewmodel.home.onlinepurchasing.OnlinePurchasingViewModel;

import java.util.logging.Logger;

@Route("online-purchasing")
@PageTitle("Online Purchasing page")
public class OnlinePurchasingView extends AbstractLayoutView {

    private static final Logger log = Logger.getLogger(OnlinePurchasingView.class.getName());
    private final OnlinePurchasingViewModel viewModel;
    private Order order;
    private Payment payment;

    @Autowired
    public OnlinePurchasingView(OnlinePurchasingViewModel viewModel) {
        super(EnumPageURL.ONLINE_PURCHASE);

        this.viewModel = viewModel;
        order = (Order) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName());
        payment = (Payment) UI.getCurrent().getSession().getAttribute(EnumSessionData.PAYMENT.getName());
        viewModel.saveData(this);
    }

    public Order getOrder() {
        return order;
    }

    public Payment getPayment() {
        return payment;
    }
}

