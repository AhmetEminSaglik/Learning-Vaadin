package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.dto.MealCartDto;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import org.vaadin.aes.view.home.core.CustomHtmlComponents;
import org.vaadin.aes.view.home.core.notificationn.CustomNotification;
import viewmodel.home.FoodViewModel;

import java.util.List;
import java.util.logging.Logger;

@Route("food-page")
public class FoodView extends AbstractLayoutView {
    private static final Logger log = Logger.getLogger(FoodView.class.getName());
    private final FoodViewModel viewModel;
    private OrderBasketView orderBasketView = new OrderBasketView();
    private final Grid<Meal> gridAllMeals = new Grid<>(Meal.class);
    private final Grid<MealCartDto> gridCustomerMeals = new Grid<>(MealCartDto.class);

    public FoodView() {
        super(EnumPageURL.FOOD_PAGE);
        viewModel = new FoodViewModel(this);
        addOrderBasketToHeader();
        VerticalLayout allMealLayout = createAllMealLayout("All Meals", viewModel.getMeals());
        VerticalLayout customerMealLayout = createCustomerMealLayout("My Meals", orderBasketView.getMealCartDtoList());

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        HorizontalLayout bodyHorizontalLayout = new HorizontalLayout();
        bodyHorizontalLayout.setWidthFull();
        bodyHorizontalLayout.setHeightFull();
        bodyHorizontalLayout.getStyle().set("background-color", "red");

        bodyHorizontalLayout.add(allMealLayout);
        bodyHorizontalLayout.add(customerMealLayout);

        getBody().add(bodyHorizontalLayout);
    }

    private VerticalLayout createAllMealLayout(String title, List<Meal> meals) {
        VerticalLayout verticalLayout = createLayoutWithName(title);
        verticalLayout.setHeightFull();
        verticalLayout.setWidth("80%");

        gridAllMeals.setItems(meals);
        gridAllMeals.setColumns("name");
        gridAllMeals.setSizeFull();


        gridAllMeals.addComponentColumn(this::createPriceForGridComponent).setHeader("Price");
        gridAllMeals.addComponentColumn(this::createImageForGridComponent).setHeader("Image");
        gridAllMeals.addComponentColumn(this::createAddButtonForGridComponent).setHeader("Process");


        verticalLayout.add(gridAllMeals);
        return verticalLayout;
    }

    private VerticalLayout createCustomerMealLayout(String title, List<MealCartDto> meals) {
        VerticalLayout verticalLayout = createLayoutWithName(title);
//        verticalLayout.setHeightFull();
//        verticalLayout.setWidth("60%");
        verticalLayout.setAlignItems(Alignment.START);
        verticalLayout.setSizeFull();


        log.info("createCustomerMealLayout meal size : " + meals.size());
//        gridCustomerMeals.setItems(meals);
//        gridCustomerMeals.setColumns("name");
        gridCustomerMeals.removeAllColumns();
        gridCustomerMeals.setWidth("100%");
        gridCustomerMeals.setHeight("100%");


        gridCustomerMeals.addComponentColumn(e -> createNameForGridComponent(e.getMeal())).setHeader("Name");
        gridCustomerMeals.addComponentColumn(e -> createPriceForGridComponent(e.getMeal())).setHeader("Price");
        gridCustomerMeals.addComponentColumn(e -> createImageForGridComponent(e.getMeal())).setHeader("Image");
        gridCustomerMeals.addComponentColumn(this::createQuantityAndPriceGridComponent).setHeader("Quantity & Price");
        gridCustomerMeals.addComponentColumn(e -> createRemoveButtonForGridComponent(e.getMeal())).setHeader("Process");

        verticalLayout.add(gridCustomerMeals);

        return verticalLayout;
    }

    private Paragraph createNameForGridComponent(Meal meal) {
        Paragraph priceParagraph = new Paragraph(meal.getName());
//        priceParagraph.getStyle().set("font-weight", "bold");
        return priceParagraph;
    }

    private Paragraph createPriceForGridComponent(Meal meal) {
        Paragraph priceParagraph = new Paragraph(meal.getPrice() + " TL");
//        priceParagraph.getStyle().set("font-weight", "bold");
        return priceParagraph;
    }

    private Image createImageForGridComponent(Meal meal) {
        Image image = new Image(meal.getThumbnail(), "Meal Image");
        image.setWidth("100px");
        image.setHeight("100px");
        image.getStyle().set("border-radius", "25px");
        return image;
    }

    private Button createAddButtonForGridComponent(Meal meal) {
        Button button = new Button("Add");
        button.addClickListener(e -> {
            viewModel.addItemToOrderBasket(meal);
        });
        return button;
    }

    private VerticalLayout createQuantityAndPriceGridComponent(MealCartDto mealCartDto) {
        int quantity = mealCartDto.getQuantity();
        double price = mealCartDto.getMeal().getPrice();
        double totalPrice = price * quantity;

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();


//        Span paragQuantity = new Span("Quantity : " + totalPrice);
        String quantityText = "Quantity : " + quantity;
//        paragQuantity.getStyle().set("font-weight", "bold");
//        paragQuantity.getStyle().set("font-size", "15px");

//        Span paragPrice = new Span("Price : " + totalPrice);
        String priceText = "Price : " + price;
//        paragPrice.getStyle().set("font-weight", "bold");
//        paragPrice.getStyle().set("font-size", "15px");

        Span paragTotalprice = new Span("total price: " + totalPrice);

        paragTotalprice.getStyle().set("font-weight", "bold");
        paragTotalprice.getStyle().set("font-size", "15px");

        verticalLayout.add(quantityText);
        verticalLayout.add(new HtmlComponent("br"));
        verticalLayout.add(priceText);
//        verticalLayout.add(new HtmlComponent("br"));
        verticalLayout.add(paragTotalprice);
        return verticalLayout;
    }

    private Button createRemoveButtonForGridComponent(Meal meal) {
        Button button = new Button("Remove");
        button.addClickListener(e -> {
            viewModel.removeItemOrderBasket(meal);
            /*if (meals.contains(meal)) {
                meals.remove(meal);
                CustomNotification.show("Meal is removed successfully from the Cart.");
            } else {
                CustomNotification.show("This meal has not been added before!");
            }*/

        });
        return button;
    }

    /*private Grid<Meal> createAllMealGrid() {


        grid.setItems(viewModel.getMeals());
        grid.setColumns("name");

        grid.setWidth("50%");
        grid.setHeight("100%");


        grid.addComponentColumn(meal -> {
            Paragraph priceParagraph = new Paragraph(meal.getPrice() + " TL");
            priceParagraph.getStyle().set("font-weight", "bold");
            return priceParagraph;

        }).setHeader("Image");

        grid.addComponentColumn(meal -> {
            Image image = new Image(meal.getThumbnail(), "Meal Image");
            image.setWidth("100px");
            image.setHeight("100px");
            image.getStyle().set("border-radius", "25px");
            return image;

        }).setHeader("Image");
        grid.addComponentColumn(meal -> {
            Button button = new Button("Add");
            button.addClickListener(e -> {
//                log.info(meal.getName() + " is added to fake basket");
                viewModel.addItemToOrderBasket(meal);
            });
            return button;
        });

        return grid;
    }*/

    private Div createOrderBasketDiv() {
//        return CustomHtmlComponents.getDivPercent("red", 30, 30);
        return CustomHtmlComponents.DivUtil.getDivPercent("red", 100, 100);
    }

    private void addOrderBasketToHeader() {
        Div divOrderBasket = createOrderBasketDiv();
        divOrderBasket.add(orderBasketView);
        customHeader.addToRightNavBar(divOrderBasket);
    }

    public FoodViewModel getViewModel() {
        return viewModel;
    }

    public OrderBasketView getOrderBasketView() {
        return orderBasketView;
    }

    private VerticalLayout createLayoutWithName(String title) {
        HtmlComponent titleComponent = new H2(title);
        return new VerticalLayout(titleComponent);
    }

    public Grid<Meal> getGridAllMeals() {
        return gridAllMeals;
    }

    public Grid<MealCartDto> getGridCustomerMeals() {
        return gridCustomerMeals;
    }
}
