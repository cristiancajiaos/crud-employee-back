package com.example.crudemployeeback.repository;

import com.example.crudemployeeback.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
