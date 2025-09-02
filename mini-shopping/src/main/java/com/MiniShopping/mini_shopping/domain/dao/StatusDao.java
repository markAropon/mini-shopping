package com.MiniShopping.mini_shopping.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiniShopping.mini_shopping.domain.entity.Status;

@Repository
public interface StatusDao extends JpaRepository<Status, Long> {
    Status findByStatusName(String statusName);
}
