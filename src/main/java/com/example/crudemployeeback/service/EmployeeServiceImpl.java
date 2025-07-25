package com.example.crudemployeeback.service;

import com.example.crudemployeeback.entity.Employee;
import com.example.crudemployeeback.record.EmployeeDTO;
import com.example.crudemployeeback.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
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

  @Override
  public Optional<EmployeeDTO> getEmployeeById(Long id) {
    return employeeRepository.findById(id).map(this::convertToDTO);
  }

  @Override
  public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
    Employee foundEmployee = employeeRepository.findById(id).orElseThrow();
    foundEmployee.setFullName(employeeDTO.fullName());
    foundEmployee.setUsername(employeeDTO.username());
    foundEmployee.setEmail(employeeDTO.email());
    foundEmployee.setPassword(employeeDTO.password());
    Employee updatedEmployee = employeeRepository.save(foundEmployee);
    return convertToDTO(updatedEmployee);
  }

  @Override
  public EmployeeDTO deleteEmployee(Long id) {
    Employee foundEmployee = employeeRepository.findById(id).orElseThrow();
    employeeRepository.deleteById(id);
    return convertToDTO(foundEmployee);
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
