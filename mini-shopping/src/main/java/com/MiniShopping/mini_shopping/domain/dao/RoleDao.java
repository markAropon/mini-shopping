package com.MiniShopping.mini_shopping.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiniShopping.mini_shopping.domain.entity.Role;



@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

Role findByRoleName(String roleName);
}