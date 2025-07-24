package com.example.crudemployeeback.service;

import com.example.crudemployeeback.record.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<EmployeeDTO> getAllEmployees();

  EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

  Optional<EmployeeDTO> getEmployeeById(Long id);
}
