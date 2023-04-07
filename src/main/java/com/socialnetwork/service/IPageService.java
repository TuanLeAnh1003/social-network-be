package com.socialnetwork.service;

import com.socialnetwork.model.Page;

public interface IPageService {
    Page savePost(Page page);

    Page getPageById(Long id);

}
