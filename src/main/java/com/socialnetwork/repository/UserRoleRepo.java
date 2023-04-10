package com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    
}
