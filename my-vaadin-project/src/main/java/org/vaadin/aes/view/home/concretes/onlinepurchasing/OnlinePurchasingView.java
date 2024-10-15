package org.vaadin.aes.view.home.concretes.onlinepurchasing;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.Payment;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.service.abstracts.address.AddressService;
import org.vaadin.aes.service.meal.MealService;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import viewmodel.home.onlinepurchasing.OnlinePurchasingViewModel;

import java.util.logging.Logger;

@Route("online-purchasing")
@PageTitle("Online Purchasing page")
public class OnlinePurchasingView extends AbstractLayoutView {

    private static final Logger log = Logger.getLogger(OnlinePurchasingView.class.getName());
    private final OnlinePurchasingViewModel viewModel;

    private final OrderService orderService;
    private final AddressService addressService;
    private final MealService mealService;
    private Order order;
    private Payment payment;

    @Autowired
    public OnlinePurchasingView(OrderService orderService, AddressService addressService, MealService mealService) {
        super(EnumPageURL.ONLINE_PURCHASE);
        this.orderService = orderService;
        this.addressService = addressService;

        viewModel = new OnlinePurchasingViewModel(this
                , orderService
                , addressService
                , mealService);
        order = (Order) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName());
        payment = (Payment) UI.getCurrent().getSession().getAttribute(EnumSessionData.PAYMENT.getName());

        log.info("Retrieved Order:  " + order);
        log.info("Retrieved Payment:  " + payment);
        log.info("orderService orderService:  " + orderService);

        viewModel.saveData();
        this.mealService = mealService;
    }

    public Order getOrder() {
        return order;
    }

    public Payment getPayment() {
        return payment;
    }
}

