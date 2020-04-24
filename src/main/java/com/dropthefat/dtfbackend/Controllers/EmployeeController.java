package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Employee;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.EmployeeServices;

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
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServices employeeServices;

    // Get all employee
    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
    public List<Employee> getEmployee(){
        try {
            List<Employee> result = employeeServices.getAllEmployee();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // Get specific employee with parameter id
    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET, params = "id")
    public Employee getEmployee(@RequestParam("id") String id){
        try{
            Employee result = employeeServices.getEmployeeById(id);
            return result;
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public Response addEmployee(@RequestBody Employee employee){
        try{
            Response res = employeeServices.addEmployee(employee);
            logger.info("Add Employee id = " + res.getDocId() + " success!");
            return res;
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public Response updateEmployee(@RequestParam("id") String id, @RequestBody Employee employee){
        try{
            return employeeServices.updateEmployee(id, employee);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public Response deleteEmployee(@RequestParam("id") String id){
        try{
            return employeeServices.deleteEmployee(id);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}