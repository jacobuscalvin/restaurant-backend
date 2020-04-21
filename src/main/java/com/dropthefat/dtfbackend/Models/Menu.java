package com.dropthefat.dtfbackend.Models;

public class Menu {
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
}