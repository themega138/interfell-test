package com.interfell.fullstacktest.services;

import com.interfell.fullstacktest.services.dtos.Authentication;

public interface AuthenticationService {
  Authentication login(String username, String password);
}
