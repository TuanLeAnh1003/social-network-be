package com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.model.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {
    
}
