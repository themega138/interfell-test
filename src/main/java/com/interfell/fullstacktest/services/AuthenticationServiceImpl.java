package com.interfell.fullstacktest.services;

import com.interfell.fullstacktest.domain.Token;
import com.interfell.fullstacktest.domain.User;
import com.interfell.fullstacktest.repositories.TokensRepository;
import com.interfell.fullstacktest.repositories.UsersRepository;
import com.interfell.fullstacktest.services.dtos.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private TokensRepository tokensRepository;

  @Value("${interfell.security.token-time-alive:3600}")
  private Long tokenTimeAlive;

  @Override
  public Authentication login(String username, String password) {
    Optional<User> optionalUser = usersRepository.findOneByUsername(username);
    if(!optionalUser.isPresent()) throw new RuntimeException("User not found...");
    Optional<Token> optionalToken = tokensRepository.findFirstByExpirationTimeIsLessThanAndUser(new Date().getTime(), optionalUser.get());
    Authentication authentication;
    if(!optionalToken.isPresent()){
      Token token = new Token();
      token.setCreationTime(new Date().getTime());
      token.setTimeAlive(tokenTimeAlive);
      token.setExpirationTime(token.getCreationTime() + tokenTimeAlive);
      token.setUser(optionalUser.get());
      token.setValue(UUID.randomUUID().toString());
      token = tokensRepository.save(token);
      authentication = new Authentication(token.getValue(),token.getExpirationTime());
    } else {
      Token token = optionalToken.get();
      authentication = new Authentication(token.getValue(),token.getExpirationTime());
    }
    return authentication;
  }
}
