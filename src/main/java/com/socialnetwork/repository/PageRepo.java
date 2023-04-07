package com.socialnetwork.repository;

import com.socialnetwork.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepo extends JpaRepository<Page, Long> {
    
}