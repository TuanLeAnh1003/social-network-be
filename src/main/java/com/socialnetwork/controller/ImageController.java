package com.socialnetwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.model.Image;
import com.socialnetwork.service.IImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImageController {
    private final IImageService imageService;
    @PostMapping("/image/save")
    public ResponseEntity<Image> save(@RequestBody Image image) {
        return ResponseEntity.ok().body(imageService.save(image));
    }
}
