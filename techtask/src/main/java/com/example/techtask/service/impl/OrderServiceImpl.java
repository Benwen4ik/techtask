package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {


    @PersistenceContext
    private EntityManager entityManager;

    public Order findOrder() {
        return entityManager.createQuery("FROM Order o  WHERE o.quantity > 1 ORDER BY o.createdAt DESC LIMIT 1",
                                Order.class).getSingleResult();

    }


    public List<Order> findOrders() {
        return entityManager.createQuery("FROM Order o WHERE o.userId in ( SELECT id FROM User u WHERE u.userStatus = 'ACTIVE') ORDER BY createdAt",
                                Order.class).getResultList();
    }
}
