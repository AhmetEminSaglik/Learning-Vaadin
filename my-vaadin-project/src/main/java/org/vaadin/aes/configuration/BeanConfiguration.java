package org.vaadin.aes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vaadin.aes.view.home.concretes.PaymentMethodView;
import org.vaadin.aes.view.home.concretes.address.AddressFormView;
import org.vaadin.aes.view.home.concretes.payment.PaymentBottomView;
import org.vaadin.aes.view.home.concretes.payment.method.PaymentMethodFormView;
import org.vaadin.aes.viewmodel.home.payment.form.PaymentBottomViewModel;

@Configuration
public class BeanConfiguration {

    @Scope("prototype")
    @Bean
    public PaymentMethodView paymentMethodView(PaymentMethodFormView paymentMethodFormView,
                                               PaymentBottomView paymentBottomView,
                                               AddressFormView addressFormView) {
        return new PaymentMethodView(paymentMethodFormView, paymentBottomView, addressFormView);
    }

    @Scope("prototype")
    @Bean
    public PaymentBottomView paymentBottomView(PaymentBottomViewModel viewModel) {
        return new PaymentBottomView(viewModel);
    }

    @Scope("prototype")
    @Bean
    public PaymentBottomViewModel paymentBottomViewModel() {
        return new PaymentBottomViewModel();
    }
}
