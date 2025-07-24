package com.example.crudemployeeback.service;

import com.example.crudemployeeback.record.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

  List<EmployeeDTO> getAllEmployees();

  EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}
