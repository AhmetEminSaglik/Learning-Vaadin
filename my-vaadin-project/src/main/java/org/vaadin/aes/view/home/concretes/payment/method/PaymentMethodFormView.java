package org.vaadin.aes.view.home.concretes.payment.method;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.view.home.concretes.payment.method.option.PaymentMethodCashForm;
import org.vaadin.aes.view.home.concretes.payment.method.option.PaymentMethodCreditCardForm;
import org.vaadin.aes.viewmodel.home.payment.form.PaymentMethodFormViewModel;
import org.vaadin.aes.viewmodel.home.service.OrderPurchaseValidator;

import java.util.logging.Logger;

@Component
public class PaymentMethodFormView extends VerticalLayout implements OrderPurchaseValidator {

    private static final Logger log = Logger.getLogger(PaymentMethodFormView.class.getName());
    private final PaymentMethodFormViewModel viewModel;// = new PaymentMethodFormViewModel(this);
    private EnumPaymentMethod selectedMethod;
    private OrderPurchaseValidator orderPurchaseValidator;
    private final RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
    private PaymentMethodCashForm paymentCashForm = new PaymentMethodCashForm();
    private PaymentMethodCreditCardForm paymentCreditCardForm;// = new PaymentMethodCreditCardForm();

    public PaymentMethodFormView(PaymentMethodFormViewModel viewModel, PaymentMethodCreditCardForm paymentCreditCardForm) {
//        setSizeFull();
        this.viewModel=viewModel;
        this.paymentCreditCardForm = paymentCreditCardForm;
        setHeightFull();
        setWidth("30%");

        viewModel.activateCreditCardPaymentMethod(this);

        addFormTitleToLayout();
        createPaymentSelectionOptions();
        //todo bu alt satir kaldirilacak
//        viewModel.updateSelectedOptionVisibility();
        addComponentsToLayout();
        addValueChangeListenerToRadioGroup();
    }

    private void addFormTitleToLayout() {
        HtmlComponent formTitle = new H2("Payment Methods");
        HtmlComponent formTitleLine = new Hr();
        add(formTitle);
        add(formTitleLine);
    }

    private void addComponentsToLayout() {
        add(paymentCreditCardForm);
        add(paymentCashForm);
    }


    private void addValueChangeListenerToRadioGroup() {
        radioGroup.addValueChangeListener(event -> {
            selectedMethod = EnumPaymentMethod.valueOf(event.getValue().toUpperCase());
            radioGroup.setValue(selectedMethod.getName());
            viewModel.updateSelectedOptionVisibility(this);
        });
    }

    private void createPaymentSelectionOptions() {
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//        radioGroup.setLabel("Payment Method Options");
        radioGroup.setItems(EnumPaymentMethod.CARD.getName(), EnumPaymentMethod.CASH.getName());
        radioGroup.setValue(selectedMethod.getName());
        add(radioGroup);
    }

    public EnumPaymentMethod getSelectedMethod() {
        return selectedMethod;
    }

    public void setSelectedMethod(EnumPaymentMethod selectedMethod) {
        this.selectedMethod = selectedMethod;
    }

    public PaymentMethodCashForm getPaymentCashForm() {
        return paymentCashForm;
    }

    public OrderPurchaseValidator getOrderPurchaseValidator() {
        return orderPurchaseValidator;
    }

    public void setOrderPurchaseValidator(OrderPurchaseValidator orderPurchaseValidator) {
        this.orderPurchaseValidator = orderPurchaseValidator;
    }

    public PaymentMethodCreditCardForm getPaymentCreditCardForm() {
        return paymentCreditCardForm;
    }

    @Override
    public boolean isValid() {
        return orderPurchaseValidator.isValid();
    }
}
