package com.yr.springbootsecuritymybatiscrud.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UUser {

  private Integer id;
  private String nickname;
  private String username;
  private String password;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp lastLoginTime;
  private Integer status;


  @Override
  public String toString() {
    return "UUser{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", createTime=" + createTime +
            ", lastLoginTime=" + lastLoginTime +
            ", status=" + status +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
