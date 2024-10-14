package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.dto.Order;
import org.vaadin.aes.view.core.CashFormatUtil;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.view.home.concretes.payment.method.option.PaymentMethodCashForm;
import org.vaadin.aes.view.home.concretes.payment.method.option.PaymentMethodCreditCardForm;
import viewmodel.home.PaymentMethodViewModel;

import java.util.List;
import java.util.logging.Logger;

@Route("payment-method")
@PageTitle("Payment Method Page")
public class PaymentMethodView extends AbstractLayoutView {

    private static final Logger log = Logger.getLogger(PaymentMethodView.class.getName());
    private final PaymentMethodViewModel viewModel;
    private List<Order> orderList;
    private EnumPaymentMethod selectedMethod = EnumPaymentMethod.CASH;
    private final RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
    private PaymentMethodCashForm paymentCashForm = new PaymentMethodCashForm();
    private PaymentMethodCreditCardForm paymentCreditCardForm = new PaymentMethodCreditCardForm();

    public PaymentMethodView() {
        super(EnumPageURL.PAYMENT_METHOD);
        this.orderList = (List<Order>) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_LIST.getName());
        this.viewModel = new PaymentMethodViewModel();

//        showOrderInGridView();

        createPaymentSelectionOptions();
        updateSelectedOptionVisibility();
        showPaymentMethodOptions();
        addValueChangeListenerToRadioGroup();
    }

    private void showPaymentMethodOptions() {
        getBody().add(paymentCreditCardForm);
        getBody().add(paymentCashForm);
    }


    private void updateSelectedOptionVisibility() {
        switch (selectedMethod) {
            case CARD:
                paymentCreditCardForm.activate();
                paymentCashForm.deactivate();
                break;
            case CASH:
                paymentCashForm.activate();
                paymentCreditCardForm.deactivate();
                break;
            default:
                log.info("Invalid Choose : " + selectedMethod);
        }
    }

    private void addValueChangeListenerToRadioGroup() {
        radioGroup.addValueChangeListener(event -> {

            log.info("event: "+event);
            if(event.getValue()==null){
                log.severe("Event value null geldi");
                return;
            }
            log.info("event.getValue(): "+event.getValue());
            log.info("event.getValue().toUpperCase(): "+event.getValue().toUpperCase());
            selectedMethod = EnumPaymentMethod.valueOf(event.getValue().toUpperCase());
            log.info("Selected Radio button value: " + selectedMethod.getName());
            radioGroup.setValue(selectedMethod.getName());
            updateSelectedOptionVisibility();
        });
    }

    private void createPaymentSelectionOptions() {
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setLabel("Payment Method Options");
        radioGroup.setItems(EnumPaymentMethod.CARD.getName(), EnumPaymentMethod.CASH.getName());
        radioGroup.setValue(selectedMethod.getName());



        getBody().add(radioGroup);
    }

    public void showOrderInGridView() {
        Grid<Order> orderGrid = new Grid<>(Order.class);
//        mealGrid.setItems(orderList.stream().map(Order::getMeal).toList());
        orderGrid.removeAllColumns();
        setWidthFull();


        log.info("Islenecek veri seti : " + orderList);
        orderGrid.addColumn(e -> e.getMeal().getName()).setHeader("Name");
        orderGrid.addComponentColumn(e -> createImageForGridComponent(e.getMeal())).setHeader("Image");
        orderGrid.addComponentColumn(e -> createQuantityAndPriceGridComponent(e.getMeal(), e.getQuantity())).setHeader("Quantity");

        orderGrid.setItems(orderList);

        for (Grid.Column<Order> tmp : orderGrid.getColumns()) {
            tmp.setAutoWidth(true);
        }
        orderGrid.setHeight("25%");

        getBody().add(orderGrid);
    }


    private Image createImageForGridComponent(Meal meal) {
        Image image = new Image(meal.getThumbnail(), "Meal Image");
        image.setWidth("100px");
        image.setHeight("100px");
        image.getStyle().set("border-radius", "25px");
        return image;
    }

    private VerticalLayout createQuantityAndPriceGridComponent(Meal meal, int quantity) {
        double price = meal.getPrice();
        double totalPrice = price * quantity;

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();

        Span spanQuantityMultiplyPrice = new Span(quantity + " x " + price);
        Span spanQuantityMultiplyPrice2 = new Span(price + " x " + quantity);
        Span paragTotalprice = new Span(CashFormatUtil.convertTL(totalPrice));

        paragTotalprice.getStyle().set("font-weight", "bold");
        paragTotalprice.getStyle().set("font-size", "15px");
        verticalLayout.add(spanQuantityMultiplyPrice);
        verticalLayout.add(spanQuantityMultiplyPrice2);
        verticalLayout.add(paragTotalprice);
        return verticalLayout;
    }
}
