package org.vaadin.aes.view.core.header;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.view.core.CustomHtmlComponents;

//@CssImport("./style.css")
public class CustomHeader extends HorizontalLayout {
    private EnumPageURL enumPageURL;
    private VerticalLayout leftNavBar = new VerticalLayout();
    private VerticalLayout rightNavBar = new VerticalLayout();

    public CustomHeader(EnumPageURL enumPageURL) {
        this.enumPageURL = enumPageURL;
        setSpacing(false);
        setSizeOfItems();

        rightNavBar.setSpacing(false);
//        rightNavBar.setPadding(false);
        rightNavBar.setMargin(false);
        rightNavBar.setAlignItems(Alignment.START);

        Div midDiv = createDivForTitle();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        horizontalLayout.add(createImage());
//        horizontalLayout.add(getPaddingDivWidth());
        horizontalLayout.add(CustomHtmlComponents.PaddingUtil.getForWidth(30));
        horizontalLayout.add(createTitle());

        midDiv.add(horizontalLayout);
        add(leftNavBar);
        add(midDiv);
        add(rightNavBar);

    }

    Image createImage() {
        String imagePage = enumPageURL.getImagePath();
        Image image = new Image(imagePage, imagePage);
        image.addClassName("round-image");
        image.setHeight("100px");
        image.setWidth("100px");
        return image;
    }


    private HtmlComponent createTitle() {
        return new H1(enumPageURL.getName());
    }

//    private Div getPaddingDivWidth() {
//        Div paddingDiv = new Div();
//        paddingDiv.setWidth("30px");
//        return paddingDiv;
//    }

    // CSS
    private Div createDivForTitle() {
        Div div = CustomHtmlComponents.DivUtil.getDivPercent("blue", 50, 100);

        return div;
    }


    public void addToLeftNavBar(HtmlComponent component) {
        leftNavBar.add(component);
    }

    public void addToRightNavBar(HtmlComponent component) {
        rightNavBar.add(component);
    }

    private void setSizeOfItems() {
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setAlignItems(Alignment.CENTER);

        setHeight("20%");
        setWidthFull();

        leftNavBar.setWidth("25%");
        leftNavBar.setHeight("100%");
//        rightNavBar.setClassName("");

        rightNavBar.setWidth("15%");
        rightNavBar.setHeight("100%");

//        rightNavBar.getStyle().set("background-color", "lightblue");
//        rightNavBar.addClassName("my-background");
    }

}
