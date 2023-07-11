package com.yr.springbootsecuritymybatiscrud.entity;


public class UUserRole {

  private Integer uid;
  private Integer rid;

  @Override
  public String toString() {
    return "UUserRole{" +
            "uid=" + uid +
            ", rid=" + rid +
            '}';
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }


  public Integer getRid() {
    return rid;
  }

  public void setRid(Integer rid) {
    this.rid = rid;
  }

}
