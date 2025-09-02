package com.MiniShopping.mini_shopping.domain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiniShopping.mini_shopping.domain.entity.Order;
import com.MiniShopping.mini_shopping.domain.entity.Users;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> findByUser(Users user);
}
