package com.yr.springbootsecuritymybatiscrud.entity;


public class UPermission {

  private Integer id;
  private String url;
  private String name;
  private String type;
  private String mark;

  @Override
  public String toString() {
    return "UPermission{" +
            "id=" + id +
            ", url='" + url + '\'' +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", mark='" + mark + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

}
