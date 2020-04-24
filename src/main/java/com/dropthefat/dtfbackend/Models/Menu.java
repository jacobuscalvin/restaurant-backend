package com.dropthefat.dtfbackend.Models;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Menu {
  private String id;
  private String name;
  private int price; //using integer temporarily. use float later.
  private String type;

  //We need this empty constructor.
  public Menu(){ }

  public Menu(String name, int price, String type){
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  //We will ignore this ID from serialization, so that the id key won't get into the db. 
  @Exclude
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}