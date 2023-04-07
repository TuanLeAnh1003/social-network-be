package com.socialnetwork.service;

import com.socialnetwork.model.Role;
import com.socialnetwork.model.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    User getUserById(Long id);
    List<User> getUsers();

    List<User> findByName(String search, String username);
    User saveAvatar(User user);
    User saveCoverImage(User user);
}
