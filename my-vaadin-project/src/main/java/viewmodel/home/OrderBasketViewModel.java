package viewmodel.home;

import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.dto.MealCartDto;
import org.vaadin.aes.view.home.concretes.OrderBasketView;
import org.vaadin.aes.view.home.core.notificationn.CustomNotification;

import java.util.Optional;
import java.util.logging.Logger;

public class OrderBasketViewModel {
    private final OrderBasketView view;
    private static final Logger log = Logger.getLogger(OrderBasketViewModel.class.getName());

    public OrderBasketViewModel(OrderBasketView orderBasketView) {
        this.view = orderBasketView;
    }

    public void addItem(Meal meal) {
        Optional<MealCartDto> existingMealCartDto = view.getMealCartDtoList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingMealCartDto.isPresent()) {
            existingMealCartDto.get().increaseQuantity();
        } else {
            MealCartDto newMealCartDto = new MealCartDto(meal, 1);
            view.getMealCartDtoList().add(newMealCartDto);
        }

        updateCartItemSize();
    }

    public void removeItem(Meal meal) {
        Optional<MealCartDto> existingMealCartDto = view.getMealCartDtoList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingMealCartDto.isPresent()) {
            MealCartDto mealCartDto = existingMealCartDto.get();
            if (mealCartDto.getQuantity() > 1) {
                mealCartDto.decreaseQuantity();
            } else {
                view.getMealCartDtoList().remove(mealCartDto);
            }
        } else {
            CustomNotification.show(getClass().getSimpleName() + " Meal not found : " + meal.getName() + " - " + meal.getThumbnail());
        }
        updateCartItemSize();
    }

    private void updateCartItemSize() {
        view.setTotal(calculateTotalItemInOrderBasket());
        view.getMealCartDtoList().forEach(meal -> log.info("Current items in Customer's Cart: " + meal));
    }

    private int calculateTotalItemInOrderBasket() {
        int total = view.getMealCartDtoList().stream().mapToInt(MealCartDto::getQuantity).sum();
        log.info("Calculated total item value is : " + total);
        return total;

    }
}
