package org.vaadin.aes.view.core.drawer;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.aes.enums.EnumPageURL;


public class LeftDrawer extends VerticalLayout {

    private final int width = 250;

    public LeftDrawer() {
        setHeight("100%");
        setWidth(getPx(width + 10));

        Div div = createDivForDrawer();

        div.add(getItem(EnumPageURL.FOOD_PAGE));
        div.add(getItem(EnumPageURL.MY_ORDERS));
//        div.add(getItem(EnumPageURL.PAYMENT));
        div.add(getLogoutItem());

        add(div);
    }

    private HorizontalLayout getItem(EnumPageURL enumPageURL) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        NativeLabel itemNameLabel = new NativeLabel(enumPageURL.getName());
        horizontalLayout.add(getDrawerItemImage(), itemNameLabel);

        horizontalLayout.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(enumPageURL.getUrl()));
        });

        horizontalLayout.add(getHeightSpace());

        return horizontalLayout;
    }

    private HorizontalLayout getLogoutItem() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        final EnumPageURL enumPageURL = EnumPageURL.EXIT;
        NativeLabel itemNameLabel = new NativeLabel(enumPageURL.getName());
        horizontalLayout.add(getDrawerItemImage(), itemNameLabel);

        horizontalLayout.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(enumPageURL.getUrl()));
            UI.getCurrent().getSession().close();
        });

        horizontalLayout.add(getHeightSpace());
        return horizontalLayout;
    }

    private Image getDrawerItemImage() {
        Image checkmark = new Image("images/right-arrow.png", "Checkmark");
        checkmark.setWidth("24px");
        checkmark.setHeight("24px");
        return checkmark;
    }


    // CSS
    /*private Div createDivForDrawer() {
        Div div = new Div();
        div.addClassName("custom-drawer");
        return div;
    }*/
// CSS
    private HtmlComponent getHeightSpace() {
        Div div = new Div();
        div.setHeight("30px");
        return div;
    }

    private Div createDivForDrawer() {
        Div div = new Div();
        div.addClassName("custom-drawer");
        div.setHeight("100%");
        div.setWidth(getPx(width));
        div.getStyle().set("border", "5px solid green");
        div.getStyle().set("padding", "5px");
        div.getStyle().set("border-radius", "5px");

        return div;
    }

    private String getPx(int val) {
        return val + "px";
    }
}
