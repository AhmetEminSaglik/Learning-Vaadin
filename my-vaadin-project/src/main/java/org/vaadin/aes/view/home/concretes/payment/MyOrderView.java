package org.vaadin.aes.view.home.concretes.payment;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.view.core.CashFormatUtil;

import java.util.List;

public class MyOrderView extends VerticalLayout {
    private final HtmlComponent formTitle = new H2("My Orders");
    private final HtmlComponent formTitleLine = new Hr();
    private final List<OrderConcept> orderConceptList;

    public MyOrderView(List<OrderConcept> orderConceptList) {
        this.orderConceptList = orderConceptList;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        Grid<OrderConcept> orderGrid = new Grid<>(OrderConcept.class);
        orderGrid.removeAllColumns();

        orderGrid.addColumn(e -> e.getMeal().getName()).setHeader("Name");
        orderGrid.addComponentColumn(e -> createImageForGridComponent(e.getMeal())).setHeader("Image");
        orderGrid.addComponentColumn(e -> createQuantityAndPriceGridComponent(e.getMeal(), e.getQuantity())).setHeader("Quantity");

        orderGrid.setItems(orderConceptList);

        for (Grid.Column<OrderConcept> tmp : orderGrid.getColumns()) {
            tmp.setAutoWidth(true);
        }
        add(formTitle, formTitleLine, orderGrid);
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
