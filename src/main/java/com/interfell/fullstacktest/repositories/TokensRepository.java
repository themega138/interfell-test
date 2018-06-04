package com.interfell.fullstacktest.repositories;

import com.interfell.fullstacktest.domain.Token;
import com.interfell.fullstacktest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Integer> {

  Optional<Token> findOneByValue(String value);

  Optional<Token> findFirstByExpirationTimeIsLessThanAndUser(Long expirationTime, User user);

}
