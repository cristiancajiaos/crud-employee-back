package com.example.crudemployeeback.service;

import com.example.crudemployeeback.entity.Employee;
import com.example.crudemployeeback.record.EmployeeDTO;
import com.example.crudemployeeback.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(
      EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }


  @Override
  public List<EmployeeDTO> getAllEmployees() {
    return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
    Employee employee = convertToEntity(employeeDTO);
    Employee savedEmployee = employeeRepository.save(employee);
    return convertToDTO(savedEmployee);
  }

  private Employee convertToEntity(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    employee.setId(employeeDTO.id());
    employee.setFullName(employeeDTO.fullName());
    employee.setUsername(employeeDTO.username());
    employee.setEmail(employeeDTO.email());
    employee.setPassword(employeeDTO.password());
    return employee;
  }

  private EmployeeDTO convertToDTO(Employee employee) {
    return new EmployeeDTO(employee.getId(), employee.getFullName(), employee.getUsername(),
        employee.getEmail(), employee.getPassword());
  }
}
