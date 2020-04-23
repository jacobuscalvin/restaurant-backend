package com.dropthefat.dtfbackend.Controllers;

import java.util.List;

import com.dropthefat.dtfbackend.Models.Employee;
import com.dropthefat.dtfbackend.Services.EmployeeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping("/getEmployee")
    public List<Employee> getEmployee(){
        try {
            List<Employee> result = employeeServices.getAllEmployee();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}