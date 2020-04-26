package com.dropthefat.dtfbackend.Models;

import java.util.List;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order {
  private String id;
  private String pic;
  private String status;
  private String tableId;
  private List<String> menu;
  private Long time;
  private int total;

  public Order(){}

  @Exclude
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTableId() {
    return tableId;
  }

  public void setTableId(String tableId) {
    this.tableId = tableId;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<String> getMenu() {
    return menu;
  }

  public void setMenu(List<String> menu) {
    this.menu = menu;
  }
}