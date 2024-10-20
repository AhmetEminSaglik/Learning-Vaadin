package org.vaadin.aes.viewmodel.home.my_orders;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.model.concrete.Payment;
import org.vaadin.aes.model.concrete.my_orders.MyOrder;
import org.vaadin.aes.model.concrete.my_orders.MyOrderItem;
import org.vaadin.aes.model.dto.UserDataDto;
import org.vaadin.aes.service.abstracts.OrderConceptService;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.service.abstracts.payment.PaymentService;
import org.vaadin.aes.view.core.CashFormatUtil;
import org.vaadin.aes.view.core.OrderConceptPriceUtility;

import java.util.List;
import java.util.logging.Logger;

@Component
@UIScope
public class MyOrdersViewModel {
    private final OrderService orderService;
    private final OrderConceptService orderConceptService;
    private final PaymentService paymentService;
    private UserDataDto user;
    private MyOrder myOrder = new MyOrder();
    private static final Logger log = Logger.getLogger(MyOrdersViewModel.class.getName());

    public MyOrdersViewModel(OrderService orderService, OrderConceptService orderConceptService, PaymentService paymentService) {
        this.orderService = orderService;
        this.orderConceptService = orderConceptService;
        this.paymentService = paymentService;
        user = (UserDataDto) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());
        log.info("User is retrieved: " + user);

        retrieveAllOrders();
        retrieveAllPayments();

/*        myOrder.getItemList().forEach(e -> {
            System.out.println(e.getCreatedAt());
            System.out.println(e.getOrder());
            System.out.println(e.getPayment());
                }
        );*/
    }

    public void setMyOrderToGrid(Grid<MyOrderItem> grid) {
//        grid.removeColumnByKey("Order");
//        grid.removeColumnByKey("Payment");
        grid.removeColumn(grid.getColumns().get(1));
        grid.removeColumn(grid.getColumns().get(1));

//        grid.removeColumn(grid.getColumns().get(1));
//        grid.removeColumn(grid.getColumns().get(2));
        grid.setSizeFull();
//        grid.getColumns().get(0).setAutoWidth(true);
        grid.addComponentColumn(this::createOrderGridComponent).setHeader("Order");
        grid.addComponentColumn(this::createPaymentGridComponent).setHeader("Payment");

        grid.getColumns().forEach(e -> {
            e.setTextAlign(ColumnTextAlign.CENTER);
            e.setAutoWidth(true);
        });


        grid.setItems(myOrder.getItemList());

        grid.getDataProvider().refreshAll();
    }

    private VerticalLayout createPaymentGridComponent(MyOrderItem myOrderItem) {
        VerticalLayout paymentLayout = new VerticalLayout();
//        paymentLayout.setAlignItems(FlexComponent.Alignment.CENTER);us
        paymentLayout.setAlignItems(FlexComponent.Alignment.AUTO);
//        paymentLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        paymentLayout.setHeight("auto");
        paymentLayout.setWidth("auto");

        HorizontalLayout paymentMethodLayout = new HorizontalLayout();
        paymentMethodLayout.setHeight("auto");
        paymentMethodLayout.setWidth("auto");

        Paragraph paragraphPaymentMethodName = new Paragraph(myOrderItem.getPayment().getPaymentMethod().getName());
        paragraphPaymentMethodName.getStyle().set("font-weight", "bold");
        paymentMethodLayout.add(new Paragraph("Payment Method: "));
        paymentMethodLayout.add(paragraphPaymentMethodName);

        HorizontalLayout totalValueLayout = new HorizontalLayout();
        totalValueLayout.setHeight("auto");
        totalValueLayout.setWidth("auto");

        double value = OrderConceptPriceUtility.calculate(myOrderItem.getOrder().getOrderConcepts());
        Paragraph paragraphTotal = new Paragraph(CashFormatUtil.convertTL(value));
        paragraphTotal.getStyle().set("font-weight", "bold");

        totalValueLayout.add(new Paragraph("Total: "));
        totalValueLayout.add(paragraphTotal);

        paymentLayout.add(paymentMethodLayout);
        paymentLayout.add(totalValueLayout);

        return paymentLayout;
    }

    private VerticalLayout createOrderGridComponent(MyOrderItem myOrderItem) {
        VerticalLayout orderVerticalLayout = new VerticalLayout();
        orderVerticalLayout.setHeight("auto");
        orderVerticalLayout.setWidth("auto");
        orderVerticalLayout.setAlignItems(FlexComponent.Alignment.AUTO);
        int counter = 1;

        List<OrderConcept> orderConceptList = myOrderItem.getOrder().getOrderConcepts();
        for (int i = 0; i < orderConceptList.size(); i++) {
            OrderConcept orderConcept = orderConceptList.get(i);
//            myOrderItem.getOrder().getOrderConcepts().stream().forEach(e -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setHeight("auto");
            horizontalLayout.setWidth("auto");
            horizontalLayout.setAlignItems(FlexComponent.Alignment.AUTO);

            VerticalLayout orderConceptVerticalLayout = new VerticalLayout();
            orderConceptVerticalLayout.setHeight("auto");
            orderConceptVerticalLayout.setWidth("auto");


            orderConceptVerticalLayout.add(new Span(orderConcept.getMeal().getName()));
            orderConceptVerticalLayout.add(new Span(CashFormatUtil.convertTL(orderConcept.getMeal().getPrice()) + " X " + orderConcept.getQuantity()));


            horizontalLayout.add(new Span(counter + "-)"));
            horizontalLayout.add(createImageOfOrder(orderConcept));
            horizontalLayout.add(orderConceptVerticalLayout);

            orderVerticalLayout.add(horizontalLayout);
            counter++;
//            });

        }
        /*myOrderItem.getOrder().getOrderConcepts().stream().forEach(e -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setHeight("auto");
            horizontalLayout.setWidthFull();

            VerticalLayout orderConceptVerticalLayout = new VerticalLayout();
            orderConceptVerticalLayout.setHeight("auto");
            orderConceptVerticalLayout.setWidthFull();


            orderConceptVerticalLayout.add(e.getMeal().getName());
            orderConceptVerticalLayout.add(CashFormatUtil.convertTL(e.getMeal().getPrice()) + " x " + e.getQuantity());


            horizontalLayout.add(new H3(counter + "-)"));
            horizontalLayout.add(createImageOfOrder(e));
            horizontalLayout.add(orderConceptVerticalLayout);

            orderVerticalLayout.add(horizontalLayout);
            counter++;
        });*/
        return orderVerticalLayout;
    }

    private Image createImageOfOrder(OrderConcept orderConcept) {
        Image image = new Image(orderConcept.getMeal().getThumbnail(), "Meal Image");
        image.setWidth("100px");
        image.setHeight("100px");
        image.getStyle().set("border-radius", "25px");
        return image;
    }

    private void printList(List<?> list) {
        list.forEach(e -> {
            log.info("List: " + list);
        });
    }

    private void retrieveAllOrders() {
//        log.info("retrieveAllOrders: START");
        List<Order> orderList = orderService.findAllByUserId(user.getId());
        System.out.println("Retrieved Order: " + orderList.get(0));
        for (Order order : orderList) {
            MyOrderItem item = new MyOrderItem();
            item.setOrder(order);
            item.setCreatedAt(order.getCreatedAt());
            myOrder.getItemList().add(item);
        }
//        log.info("------------------------------- ORDER LIST  size: " + orderList.size());
//        printList(orderList);
//        log.info("retrieveAllOrders: END");
    }

    private void retrievedAllOrderConcepts() {
        log.info("retrievedAllOrderConcepts: START");
        log.info("myOrder.getItemList().size: " + myOrder.getItemList().size());
        myOrder.getItemList().forEach(e -> {
            List<OrderConcept> orderConceptList = orderConceptService.findAllByOrderId(e.getOrder().getId());
//            e.setOrderConceptList(orderConceptList);
            System.out.println("Retrieved Order Concept List: " + orderConceptList.get(0));
//            log.info("------------------------------- Order CONCEPT List size:  orderId: " + e.getOrder().getId() + " ---- Order Concept Size: " + orderConceptList.size());
        });
        log.info("retrievedAllOrderConcepts: END");
    }

    private void setCreatedAtToItems() {
        log.info("setCreatedAtToItems: START");
        myOrder.getItemList().forEach(e -> {
            e.setCreatedAt(e.getOrder().getCreatedAt());
        });
        log.info("setCreatedAtToItems: END");
    }

    private void retrieveAllPayments() {
        log.info("retrieve All Payments: START");
        myOrder.getItemList().forEach(e -> {
            Payment payment = paymentService.findByOrderId(e.getOrder().getId());
            e.setPayment(payment);
            System.out.println("Retrieved Payment: " + payment);
        });
        log.info("retrieveAllPayments: END");
    }

}
