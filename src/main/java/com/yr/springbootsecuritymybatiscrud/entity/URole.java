package com.yr.springbootsecuritymybatiscrud.entity;


public class URole {

  private Integer id;
  private String name;
  private String type;

  @Override
  public String toString() {
    return "URole{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
