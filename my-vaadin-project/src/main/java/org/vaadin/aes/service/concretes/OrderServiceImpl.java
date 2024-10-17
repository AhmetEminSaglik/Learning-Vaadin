package org.vaadin.aes.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.repository.abstracts.OrderConceptRepository;
import org.vaadin.aes.repository.abstracts.OrderRepository;
import org.vaadin.aes.service.abstracts.OrderService;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConceptRepository orderConceptRepository;
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderConceptRepository orderConceptRepository) {
        this.orderRepository = orderRepository;
        this.orderConceptRepository = orderConceptRepository;
    }

    /*@Override
    public Order createOrder(List<OrderConcept> orderConceptList, Address address) {
        Order order = new Order();
        User user = (User) UI.getCurrent().getSession().getAttribute(EnumSessionData.USER_DATA.getName());
        if (user == null) {
            CustomNotification.showShort("User is null. Fake User is created");
            user = FakeData.getUser();
        }

        order.setUser(user);
        order.setAddress(address);
        order.setAddress(address);
        log.info("Order created : " + order);
        return order;
    }
*/
    @Override
    public Order save(Order order) {
        order = orderRepository.save(order);
        log.info("Data is saved: " + order);
        return order;
    }


}
