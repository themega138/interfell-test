package com.interfell.fullstacktest.services;

import com.interfell.fullstacktest.domain.User;
import com.interfell.fullstacktest.exceptions.EntityNotFoundException;
import com.interfell.fullstacktest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void init(){
        usersRepository.save(new User("admin","admin","Pablo","Perez", new HashSet<>(Arrays.asList("ROLE_ADMIN"))));
        usersRepository.save(new User("user","user","Pepe","Pirez", new HashSet<>(Arrays.asList("ROLE_USER"))));
    }

    @Override
    public User loadByUsername(String username) {
        User user = usersRepository.findOneByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found..."));
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }
}
