package com.socialnetwork.service;

import com.socialnetwork.model.Role;
import com.socialnetwork.model.User;
import com.socialnetwork.model.UserRole;
import com.socialnetwork.repository.RoleRepo;
import com.socialnetwork.repository.UserRepo;
import com.socialnetwork.repository.UserRoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            log.error("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        } else {
            log.info("user found in db");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);    
    }

    @Override
    public User saveUser(User user) {
        List<Role> checkRole = roleRepo.findAll();
        if (checkRole.size() == 0) {
            Role newRoleUser = new Role();
            newRoleUser.setId(Long.valueOf(1));
            newRoleUser.setName("ROLE_USER");
            roleRepo.save(newRoleUser);

            Role newRoleAdmin = new Role();
            newRoleAdmin.setId(Long.valueOf(2));
            newRoleAdmin.setName("ROLE_ADMIN");
            roleRepo.save(newRoleAdmin);
        }
        log.info("Saving new user: {} to db", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepo.save(user);
        addRoleToUser(newUser.getUsername(), "ROLE_USER");
        return newUser;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role: {} to db", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        UserRole newUserRole = new UserRole();
        newUserRole.setUserId(user.getId());
        newUserRole.setRoleId(role.getId());

        userRoleRepo.save(newUserRole);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {} ", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if(!userOpt.isPresent()){
            throw new RuntimeException("No user found with provided id");
        }
        return userOpt.get();
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public List<User> findByName(String search, String username) {
        return userRepo.findByName(search, username);
        
    }

    @Override
    public User saveAvatar(User user) {
        Integer result = userRepo.saveAvatar(user.getAvatar(), user.getId());

        if (result == 1) {
            Optional<User> userOpt = userRepo.findById(user.getId());

            return userOpt.get();
        } else {
            throw new Error("Cannot change avatar");
        }
    }

    @Override
    public User saveCoverImage(User user) {
        Integer result = userRepo.saveCoverImage(user.getCoverImage(), user.getId());

        if (result == 1) {
            Optional<User> userOpt = userRepo.findById(user.getId());

            return userOpt.get();
        } else {
            throw new Error("Cannot change cover image");
        }
    }
}