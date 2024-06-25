package com.spring.productmanager.controllers;

import com.spring.productmanager.entities.Employee;
import com.spring.productmanager.exception.ResourceNotFoundException;
import com.spring.productmanager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
    
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeRepository.deleteById(id);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not find employee has id = " + id));
        return ResponseEntity.ok(employee);
    }
}
