package com.socialnetwork.dto;

import java.security.Principal;
 
public class UserInter implements Principal {
 
    private String name;
 
    public UserInter(String name) {
        this.name = name;
    }
 
    @Override
    public String getName() {
        return name;
    }
 
}