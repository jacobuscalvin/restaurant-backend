package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Order;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.OrderServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class OrderController {
  Logger logger = LoggerFactory.getLogger(OrderController.class);
  
  @Autowired
  OrderServices orderServices;

  @RequestMapping(value="/order", method = RequestMethod.GET)
  public List<Order> getOrder(){
    try{
      List<Order> result = orderServices.getAllOrder();
      return result;
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  @RequestMapping(value="/order/{id}", method = RequestMethod.GET)
  public Order getOrder(@PathVariable(value="id") String id) {
    try{
      return orderServices.getOrderById(id);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  @RequestMapping(value="/order", method = RequestMethod.POST)
  public Response addOrder(@RequestBody Order order){
    try{
      Response resp = orderServices.addOrder(order);
      return resp;
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return new Response(false,"Error while adding order.");
    }
  }

  @RequestMapping(value="/order/{id}", method=RequestMethod.PUT)
  public Response updateOrder(@PathVariable(value="id") String id, @RequestBody Order order){
    try{
      //Wrap the code to make it simpler. same as line 56 & 58
      return orderServices.updateOrder(id, order);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }

  @RequestMapping(value="/order/{id}", method=RequestMethod.DELETE)
  public Response deleteOrder(@PathVariable(value="id") String id) {
    try{
      return orderServices.deleteOrder(id);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }
}