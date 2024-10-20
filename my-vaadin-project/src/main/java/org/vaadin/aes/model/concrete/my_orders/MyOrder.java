package org.vaadin.aes.model.concrete.my_orders;

import java.util.ArrayList;
import java.util.List;

public class MyOrder {

    List<MyOrderItem> itemList = new ArrayList<>();

    public List<MyOrderItem> getItemList() {
        return itemList;
    }
}
