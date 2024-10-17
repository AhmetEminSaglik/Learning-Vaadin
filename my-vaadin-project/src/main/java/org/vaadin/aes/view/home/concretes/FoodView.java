package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.view.core.CashFormatUtil;
import org.vaadin.aes.view.core.CustomHtmlComponents;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;
import viewmodel.home.FoodViewModel;

import java.util.List;
import java.util.logging.Logger;

@Route("food-page")
@PageTitle("Food Page")
public class FoodView extends AbstractLayoutView {
    private static final Logger log = Logger.getLogger(FoodView.class.getName());

    private final FoodViewModel viewModel;
    private OrderBasketView orderBasketView = new OrderBasketView();
    private final Grid<Meal> gridAllMeals = new Grid<>(Meal.class);
    private final Grid<OrderConcept> gridCustomerMeals = new Grid<>(OrderConcept.class);

    private final MealService mealService;

    @Autowired
    public FoodView(MealService mealService) {
        super(EnumPageURL.FOOD_PAGE);
        this.mealService = mealService;
        viewModel = new FoodViewModel(this,mealService);
        addOrderBasketToHeader();
        VerticalLayout allMealLayout = createAllMealLayout("All Foods ", viewModel.getMeals());
        VerticalLayout customerMealLayout = createCustomerMealLayout("My Orders", orderBasketView.getOrderConceptList());

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
        verticalLayout.setWidthFull();
        verticalLayout.setAlignItems(Alignment.CENTER);

        gridAllMeals.setItems(meals);
        gridAllMeals.setSizeFull();
        gridAllMeals.setColumns("name");
        gridAllMeals.getColumns().get(0).setAutoWidth(true);

        gridAllMeals.addComponentColumn(this::createPriceForGridComponent).setHeader("Price").setAutoWidth(true);
        gridAllMeals.addComponentColumn(this::createImageForGridComponent).setHeader("Image").setAutoWidth(true);
        gridAllMeals.addComponentColumn(this::createAddButtonForGridComponent).setHeader("Process").setAutoWidth(true);


        verticalLayout.add(gridAllMeals);
        return verticalLayout;
    }

    private VerticalLayout createCustomerMealLayout(String title, List<OrderConcept> meals) {
        VerticalLayout verticalLayout = createLayoutWithName(title);
//        verticalLayout.setHeightFull();
//        verticalLayout.setWidth("60%");
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setSizeFull();


        log.info("createCustomerMealLayout meal size : " + meals.size());
//        gridCustomerMeals.setItems(meals);
//        gridCustomerMeals.setColumns("name");
        gridCustomerMeals.removeAllColumns();
        gridCustomerMeals.setWidth("100%");
        gridCustomerMeals.setHeight("100%");


        gridCustomerMeals.addComponentColumn(e -> createNameForGridComponent(e.getMeal())).setHeader("Name").setAutoWidth(true);
        gridCustomerMeals.addComponentColumn(e -> createPriceForGridComponent(e.getMeal())).setHeader("Price").setAutoWidth(true);
        gridCustomerMeals.addComponentColumn(e -> createImageForGridComponent(e.getMeal())).setHeader("Image").setAutoWidth(true);
        gridCustomerMeals.addComponentColumn(this::createQuantityAndPriceGridComponent).setHeader("Total").setAutoWidth(true);
        gridCustomerMeals.addComponentColumn(e -> createRemoveButtonForGridComponent(e.getMeal())).setHeader("Process").setAutoWidth(true);

        verticalLayout.add(gridCustomerMeals);

        return verticalLayout;
    }

    private Paragraph createNameForGridComponent(Meal meal) {
        Paragraph priceParagraph = new Paragraph(meal.getName());
//        priceParagraph.getStyle().set("font-weight", "bold");
        return priceParagraph;
    }

    private Paragraph createPriceForGridComponent(Meal meal) {
        Paragraph priceParagraph = new Paragraph(CashFormatUtil.convertTL(meal.getPrice()));
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

    private VerticalLayout createQuantityAndPriceGridComponent(OrderConcept Order) {
        int quantity = Order.getQuantity();
        double price = Order.getMeal().getPrice();
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


    private Button createRemoveButtonForGridComponent(Meal meal) {
        Button button = new Button("Remove");
        button.addClickListener(e -> {
            viewModel.removeItemOrderBasket(meal);
            /*if (meals.contains(meal)) {
                meals.remove(meal);
                CustomNotification.showShort("Meal is removed successfully from the Cart.");
            } else {
                CustomNotification.showShort("This meal has not been added before!");
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

    public Grid<OrderConcept> getGridCustomerMeals() {
        return gridCustomerMeals;
    }
}
