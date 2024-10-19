package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order update(Order order);


    /*@Modifying
    @Transactional
    @Query("UPDATE Order o SET o = :order WHERE o.id = :id")
    int updateOrder(@Param("order") Order order, @Param("id") Long id);*/
}
