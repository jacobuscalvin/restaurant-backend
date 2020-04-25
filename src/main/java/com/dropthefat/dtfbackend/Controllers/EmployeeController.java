package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Employee;
import com.dropthefat.dtfbackend.Models.Response;
import com.dropthefat.dtfbackend.Services.EmployeeServices;

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
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServices employeeServices;

    // Get all employee
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
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
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable(value="id") String id){
        try{
            Employee result = employeeServices.getEmployeeById(id);
            return result;
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    // Add new employee
    @PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
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

    // Update exisiting employee
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public Response updateEmployee(@PathVariable(value="id") String id, @RequestBody Employee employee){
        try{
            return employeeServices.updateEmployee(id, employee);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    // Delete existing employee
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public Response deleteEmployee(@PathVariable(value="id") String id){
        try{
            return employeeServices.deleteEmployee(id);
        } catch(Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}