package org.example.hospitalapi.service;

import org.example.hospitalapi.dtos.EmployeeResponse;
import org.example.hospitalapi.dtos.PostEmployeeRequest;
import org.example.hospitalapi.dtos.UpdateEmployeeStatusRequest;
import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.mapper.EmployeeMapper;
import org.example.hospitalapi.model.Employee;
import org.example.hospitalapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeesRepository;
  private final EmployeeMapper employeeMapper = new EmployeeMapper();

  public List<EmployeeResponse> getAllEmployees() {
    List<Employee> employees = employeesRepository.findAll();
    return employeeMapper.fromEmployeeListToDtoGetEmployeeResponseList(employees);
  }

  public Optional<EmployeeResponse> getEmployeeById(Long id) {
    Optional<Employee> employeeById = employeesRepository.findById(id);
    return employeeById.map(employeeMapper::fromEmployeeToDtoEmployeeResponse);
  }

  public List<EmployeeResponse> getEmployesByStatus(String status) {
    List<Employee> employees = employeesRepository.findAllByEmployeeStatus(EmployeeStatus.valueOf(status));
    return employeeMapper.fromEmployeeListToDtoGetEmployeeResponseList(employees);
  }

  public List<EmployeeResponse> getEmployeesByDepartment(String department) {
    List<Employee> employees = employeesRepository.findAllByDepartment(department);
    return employeeMapper.fromEmployeeListToDtoGetEmployeeResponseList(employees);
  }

  /**
   *  Method to create a new employee.
   *
   * @param postEmployeeRequest Object request that must have department, name, employeeStatus (only accepted: ON_CALL, ON, OFF).
   * @return Returns a Optional<EmployeeResponse> that contain a EmployeeResponse or is empty
   */
  public Optional<EmployeeResponse> createEmployee(PostEmployeeRequest postEmployeeRequest){
    Employee newEmployee = employeeMapper.fromDtoPostEmployeeRequestToEmployee(postEmployeeRequest);
    employeesRepository.save(newEmployee);
    return Optional.of(employeeMapper.fromEmployeeToDtoEmployeeResponse(newEmployee));
  }

  public Optional<EmployeeResponse> updateStatusEmployee(Long id, UpdateEmployeeStatusRequest updateEmployeeStatusRequest){
    Optional<Employee> optionalEmployeeFromDb = employeesRepository.findById(id);
    return optionalEmployeeFromDb.map(employee -> {
      employee.setEmployeeStatus(EmployeeStatus.valueOf(updateEmployeeStatusRequest.getEmployeeStatus()));
      Employee updatedEmployee = employeesRepository.save(employee);
      return employeeMapper.fromEmployeeToDtoEmployeeResponse(updatedEmployee);
    });
    /*
    Previus way of final method:

    if(optionalEmployeeFromDb.isPresent()){
      Employee updatedEmployeeStatus = optionalEmployeeFromDb.get();
      updatedEmployeeStatus.setEmployeeStatus(EmployeeStatus.valueOf(updateEmployeeStatusRequest.getEmployeeStatus()));
      updatedEmployeeStatus = employeesRepository.save(updatedEmployeeStatus);
      return Optional.of(employeeMapper.fromEmployeeToDtoEmployeeResponse(updatedEmployeeStatus));
    }else {
      return Optional.empty();
    }
     */
  }
}
