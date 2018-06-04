package com.interfell.fullstacktest.services.dtos;

public class Authentication {

  private final String token;

  private final Long time;

  public Authentication(String token, Long time) {
    this.token = token;
    this.time = time;
  }

  public String getToken() {
    return token;
  }

  public Long getTime() {
    return time;
  }

}
