package com.tamires.course.repositories;

import com.tamires.course.entities.OrderItem;
import com.tamires.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
