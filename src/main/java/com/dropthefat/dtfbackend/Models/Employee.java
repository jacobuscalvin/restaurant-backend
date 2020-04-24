package com.dropthefat.dtfbackend.Models;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Employee {
    
    private String id;
    private String name;
    private String address;
    private String phone;
    private String position;

    public Employee(){ };

    public Employee(String name, String address, String phone, String position){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Exclude
    public String getId() {
      return id;
    }
  
    public void setId(String id) {
      this.id = id;
    }

}