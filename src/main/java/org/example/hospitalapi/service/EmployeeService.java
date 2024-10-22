package org.example.hospitalapi.service;

import org.example.hospitalapi.dtos.get.GetEmployeeResponse;
import org.example.hospitalapi.mapper.EmployeeMapper;
import org.example.hospitalapi.model.Employee;
import org.example.hospitalapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeesRepository;
  private final EmployeeMapper employeeMapper = new EmployeeMapper();

  public Optional<GetEmployeeResponse> getEmployeeById(Long id) {
    Optional<Employee> employeeById = employeesRepository.findById(id);
    return employeeById.map(employeeMapper::toDtoGetEmployeeResponse);
  }
}
