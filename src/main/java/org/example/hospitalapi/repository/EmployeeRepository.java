package org.example.hospitalapi.repository;

import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findAllById(Long id);
  List<Employee> findAll();
  List<Employee> findAllByEmployeeStatus(EmployeeStatus employeeStatus);
  List<Employee> findAllByDepartment(String department);

}
