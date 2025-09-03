package com.MiniShopping.mini_shopping.config;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.MiniShopping.mini_shopping.domain.dao.RoleDao;
import com.MiniShopping.mini_shopping.domain.dao.StatusDao;
import com.MiniShopping.mini_shopping.domain.entity.Role;
import com.MiniShopping.mini_shopping.domain.entity.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleStatusInitializer implements ApplicationRunner {

    private final RoleDao roleDao;
    private final StatusDao statusDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Initialize roles
        createRoleIfNotExists("USER", "Standard user role with limited permissions");
        createRoleIfNotExists("ADMIN", "Administrator role with full permissions");

        // Initialize statuses
        createStatusIfNotExists("PENDING");
        createStatusIfNotExists("PROCESSING");
        createStatusIfNotExists("SHIPPED");
        createStatusIfNotExists("DELIVERED");
        createStatusIfNotExists("CANCELLED");
    }

    private void createRoleIfNotExists(String roleName, String description) {
        if (roleDao.findByRoleName(roleName) == null) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setDescription(description);
            role.setDateCreated(LocalDateTime.now());
            role.setDateModified(LocalDateTime.now());
            roleDao.save(role);
            log.info("Default role '{}' created successfully", roleName);
        } else {
            log.info("Default role '{}' already exists", roleName);
        }
    }

    private void createStatusIfNotExists(String statusName) {
        if (statusDao.findByStatusName(statusName) == null) {
            Status status = new Status();
            status.setStatusName(statusName);
            status.setDateCreated(LocalDateTime.now());
            status.setDateModified(LocalDateTime.now());
            statusDao.save(status);
            log.info("Default status '{}' created successfully", statusName);
        } else {
            log.info("Default status '{}' already exists", statusName);
        }
    }
}
