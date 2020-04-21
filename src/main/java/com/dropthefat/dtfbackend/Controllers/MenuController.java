package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Menu;
import com.dropthefat.dtfbackend.Services.MenuServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

  @Autowired
  MenuServices menuServices;

  @GetMapping("/getMenu")
  public List<Menu> getMenu(){
    try{
      List<Menu> result = menuServices.getAllMenu();
      return result;
    }catch(Exception ex){
      System.out.println(ex.getMessage());
      return null;
    }
  }
}