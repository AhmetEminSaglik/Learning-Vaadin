package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.dto.MealCartDto;
import org.vaadin.aes.model.dto.UserDataDto;
import org.vaadin.aes.view.core.cssdesign.CssDesign;
import viewmodel.home.OrderBasketViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class OrderBasketView extends VerticalLayout {
    private static final Logger log = Logger.getLogger(OrderBasketView.class.getName());
// todo  burada title'i string yapinca ui iyi ioluyor ama setleyemiyoruz
// o yuzden string'i geri html componentine cevrilecek

    //    private final HtmlContainer userNameText = new Paragraph();
    private final Span spanTitle = new Span("My Cart");
    //    private final Span spanTitle = new Span("My Cart");
    //    private int itemSize = 0;
    private List<MealCartDto> mealCartDto = new ArrayList<>();
    private Button btnBuyAll = new Button("Buy All");
    private final OrderBasketViewModel viewModel = new OrderBasketViewModel(this);
    //    private final HtmlContainer total = new Paragraph("Total Item: " +0);
//    private String total = "Total Item: " + 0;
    private final Span spanTotal = new Span("Total Item: " + 0);

    public OrderBasketView() {

        UserDataDto userData = (UserDataDto) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());
        setSizeFull();
        setPadding(false);
//        setSpacing(false);
        setAlignItems(Alignment.START);

        if (userData == null) {
            userData = new UserDataDto();
            userData.setFirstName("Fake Name");
            userData.setLastName("Fake LastName");
            getUI().ifPresent(e -> {
                e.navigate("login");
                log.info("e bulundu: " + e);
            });
            log.info("will be navigated to : " + EnumPageURL.LOGIN_PAGE.getUrl());
        }
//        userNameText.setText(userData.getFirstName() + " " + userData.getLastName());
//        userNameText.setWidthFull();
//        userNameText.setHeight("5%");
//        userNameText.setHeight("30%");


//        spanTotal.setHeight("20%");
//        configureComponent(userNameText);

//        configureComponent(spanTitle);
//        configureComponent(spanTotal);

        btnBuyAll.setHeight("30px");
        btnBuyAll.setWidth("50%");
        btnBuyAll.getStyle().set("background-color", "#2bd617");
        btnBuyAll.getStyle().set("color", "black");

        VerticalLayout innerLayout = new VerticalLayout();
        Div innerDiv = new Div();
        innerDiv.setWidthFull();
        innerDiv.add(spanTitle);
        innerDiv.add(new HtmlComponent("br"));
        innerDiv.add(spanTotal);
//        configureComponent(innerDiv);
        innerLayout.setSpacing(false);
        innerLayout.setHeightFull();
        innerLayout.setWidthFull();
        innerLayout.setAlignItems(Alignment.CENTER);

//        innerLayout.add(spanTitle);
//        innerLayout.add(spanTotal);
        innerLayout.add(innerDiv);
        innerLayout.add(btnBuyAll);


        setCssOfHtmlParagraphs();
//        add(userNameText, title, total, btnBuyAll);
//        add(spanTitle);
//        add(spanTotal);
//        add(btnBuyAll);
        add(innerLayout);
    }


    private void configureComponent(HtmlContainer component) {
        component.setHeight("10%");
        component.setWidthFull();
        CssDesign.setMargin(component);
        CssDesign.setPadding(component);
    }

    private void setCssOfHtmlParagraphs() {
//        userNameText.getStyle().set("font-size", "20px");
//        title.getStyle().set("font-size", "20px");
//        total.getStyle().set("font-size", "20px");
        getStyle().set("padding", "10px");
    }

    private void setActionBtnBuyAll(Button button) {
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(e -> viewModel.buyItems());
    }

    public OrderBasketViewModel getViewModel() {
        return viewModel;
    }

    public List<MealCartDto> getMealCartDtoList() {
        return mealCartDto;
    }

    public void setTotal(int val) {
        spanTotal.setText("Total Item: " + val);
    }

    public String getTotal() {
        return spanTotal.getText();
    }
//    public HtmlContainer getTotal() {
//        return total;
//    }
}
