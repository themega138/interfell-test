package com.interfell.fullstacktest.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String value;

  private Long creationTime;

  private Long timeAlive;

  private Long expirationTime;

  @ManyToOne(fetch = FetchType.EAGER)
  private User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Long getTimeAlive() {
    return timeAlive;
  }

  public void setTimeAlive(Long timeAlive) {
    this.timeAlive = timeAlive;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Long creationTime) {
    this.creationTime = creationTime;
  }

  public Long getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(Long expirationTime) {
    this.expirationTime = expirationTime;
  }
}
