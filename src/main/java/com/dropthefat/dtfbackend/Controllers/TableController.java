package com.dropthefat.dtfbackend.Controllers;

import java.util.List;
import com.dropthefat.dtfbackend.Models.Table;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.TableServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
    Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    TableServices tableServices;

    //Get all Table, enter url without params.
    @RequestMapping(value = "/getTable",method = RequestMethod.GET)
    public List<Table> getMenu(){
        try{
        List<Table> result = tableServices.getAllTable();
        return result;
        }catch(Exception ex){
        logger.error(ex.getMessage());
        return null;
        }
    }

    //Get a specific Table with ID as param.
    @RequestMapping(value = "/getTable",method = RequestMethod.GET, params = "id")
    public Table getTable(@RequestParam("id") String id){
        try{
            Table result = tableServices.getTableById(id);
            return result;
        }catch(Exception ex){
        logger.error(ex.getMessage());
        return null;
        }
    }
     //POST request of table, to add a table
    //Response body is using seralized Table object, e.g. "tableNumber": 1, "status": "available", "type":"bar".
    @PostMapping(path="/addTable", consumes = "application/json", produces = "application/json")
    public Response addTable(@RequestBody Table table)
    {
        try{
        Response resp = tableServices.addTable(table);
        logger.info("Added table with ID " + resp.getDocId());
        return resp;
        }catch(Exception ex){
        logger.error(ex.getMessage());
        return null;
        }
    }

    @RequestMapping(value="/updateTable", method=RequestMethod.POST )
    public Response updateTable(@RequestParam("id") String id, @RequestBody Table table){
        try{
        //Wrap the code to make it simpler. same as line 56 & 58
        return tableServices.updateTable(id, table);
        }catch(Exception ex){
        logger.error(ex.getMessage());
        return null;
        }
    }

    @RequestMapping(value="/deleteTable", method=RequestMethod.DELETE)
  public Response deleteMenu(@RequestParam("id") String id) {
    try{
      return tableServices.deleteTable(id);
    }catch(Exception ex){
      logger.error(ex.getMessage());
      return null;
    }
  }


}