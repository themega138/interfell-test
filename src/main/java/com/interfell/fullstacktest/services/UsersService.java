package com.interfell.fullstacktest.services;

import com.interfell.fullstacktest.domain.User;

import java.util.List;

public interface UsersService {

    User loadByUsername(String username);
    List<User> findAllUsers();
}
