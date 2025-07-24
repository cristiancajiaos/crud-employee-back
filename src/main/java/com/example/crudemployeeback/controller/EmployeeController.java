package com.example.crudemployeeback.controller;

import com.example.crudemployeeback.entity.Employee;
import com.example.crudemployeeback.record.EmployeeDTO;
import com.example.crudemployeeback.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public List<EmployeeDTO> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("{id}")
  public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
    Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
    return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    return employeeService.createEmployee(employeeDTO);
  }

  @PutMapping("{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
    try {
      EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
      return ResponseEntity.ok(updatedEmployee);
    } catch (Exception e) {
      System.err.println("Error: " + e);
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
    try {
       EmployeeDTO deletedEmployee = employeeService.deleteEmployee(id);
       return ResponseEntity.ok(deletedEmployee);
    } catch (Exception e) {
      System.err.println("Error: " + e);
      return ResponseEntity.notFound().build();
    }
  }
}
