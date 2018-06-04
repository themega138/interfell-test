package com.interfell.fullstacktest.repositories;

import com.interfell.fullstacktest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Integer>{

    Optional<User> findOneByUsername(String username);

}
