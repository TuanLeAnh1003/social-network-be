package com.socialnetwork.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialnetwork.model.Image;
import com.socialnetwork.repository.ImageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService implements IImageService {
    private final ImageRepo imageRepo;
    @Override
    public Image save(Image image) {
        return imageRepo.save(image);
    }
    
}
