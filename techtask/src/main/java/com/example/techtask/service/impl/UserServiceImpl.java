package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    public User findUser() {
        return (User) entityManager.createNativeQuery("SELECT u.id, u.email, u.user_status FROM users u\n" +
                " JOIN ( SELECT SUM(price) as sum_price, user_id FROM orders\n" +
                "        WHERE EXTRACT(YEAR FROM created_at) = 2003 GROUP BY user_id ORDER BY sum_price DESC limit 1 ) AS tab on tab.user_id = u.id",User.class).getSingleResult();
    }

    public List<User> findUsers() {
        return entityManager.createQuery("FROM User u WHERE u.id in (SELECT userId FROM Order o WHERE o.orderStatus = 'PAID' AND EXTRACT(YEAR FROM o.createdAt) = 2010)",User.class)
                .getResultList();
    }
}
