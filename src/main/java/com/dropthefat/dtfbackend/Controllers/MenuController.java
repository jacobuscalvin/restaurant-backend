package com.dropthefat.dtfbackend.Controllers;

import java.util.List;
import com.dropthefat.dtfbackend.Models.Menu;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.MenuServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

  Logger logger = LoggerFactory.getLogger(MenuController.class);

  @Autowired
  MenuServices menuServices;

  //Get all menu, enter url without params.
  @RequestMapping(value = "/menu", method = RequestMethod.GET)
  public List<Menu> getMenu(){
    try{
      List<Menu> result = menuServices.getAllMenu();
      return result;
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  //Get a specific menu with ID as param.
  @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
  public Menu getMenu(@PathVariable(value="id") String id){
    try{
      Menu result = menuServices.getMenuById(id);
      return result;
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  //POST request of menu, to add a menu
  //Response body is using seralized Menu object, e.g. "name": "foo", "price": 100, "type":"bar".
  @PostMapping(path="/menu", consumes = "application/json", produces = "application/json")
  public Response addMenu(@RequestBody Menu menu)
  {
    try{
      Response resp = menuServices.addMenu(menu);
      logger.info("Added menu with ID " + resp.getDocId());
      return resp;
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  @RequestMapping(value="/menu/{id}", method=RequestMethod.PUT )
  public Response updateMenu(@PathVariable(value="id") String id, @RequestBody Menu menu){
    try{
      //Wrap the code to make it simpler. same as line 56 & 58
      return menuServices.updateMenu(id, menu);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  @RequestMapping(value="/menu/{id}", method=RequestMethod.DELETE)
  public Response deleteMenu(@PathVariable(value="id") String id) {
    try{
      return menuServices.deleteMenu(id);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }
  
}