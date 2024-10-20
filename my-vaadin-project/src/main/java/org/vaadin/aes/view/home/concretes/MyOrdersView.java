package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.concrete.my_orders.MyOrderItem;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.viewmodel.home.my_orders.MyOrdersViewModel;

@Route("my-orders")
@PageTitle("My Orders Page")
@UIScope
public class MyOrdersView extends AbstractLayoutView {
    private final MyOrdersViewModel viewModel;
    //private  final
    private final Grid<MyOrderItem> gridMyOrder = new Grid<>(MyOrderItem.class);

    public MyOrdersView(MyOrdersViewModel viewModel) {
        super(EnumPageURL.MY_ORDERS);
        this.viewModel = viewModel;

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        viewModel.setMyOrderToGrid(gridMyOrder);
        getBody().add(gridMyOrder);
    }
//    private VerticalLayout createMyOrderItemGrid(){
//    }


}
