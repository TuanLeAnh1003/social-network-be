package com.socialnetwork.service;

import com.socialnetwork.model.Page;
import com.socialnetwork.repository.PageRepo;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService implements IPageService {
    private final PageRepo pageRepo;

    @Override
    public Page savePost(Page page) {
        return null;
    }

    @Override
    public Page getPageById(Long id) {
        Optional<Page> pageOpt = pageRepo.findById(id);
        if(!pageOpt.isPresent()){
            throw new RuntimeException("No page found with provided id");
        }
        return pageOpt.get();
    }
}