package org.example.hospitalapi.controller;

import jakarta.validation.Valid;
import org.example.hospitalapi.dtos.EmployeeResponse;
import org.example.hospitalapi.dtos.PostEmployeeRequest;
import org.example.hospitalapi.dtos.UpdateEmployeeStatusRequest;
import org.example.hospitalapi.mapper.EmployeeMapper;
import org.example.hospitalapi.repository.EmployeeRepository;
import org.example.hospitalapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/employees")
@RestController
public class EmployeesController {

  @Autowired
  EmployeeRepository employeesRepository;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeMapper employeeMapper;

  @GetMapping("/employees")
  public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
    List<EmployeeResponse> employees = employeeService.getAllEmployees();
    return employees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
  }

  @GetMapping("/employee/{id}")
  public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
    Optional<EmployeeResponse> employee = employeeService.getEmployeeById(id);
    return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Endpoint to get all employees with same status.
   *
   * @param status Must be exactly value of enum: ON_CALL, ON, OFF
   * @return Returns a ResponseEntity<List<GetEmployeeResponse>> with that contain a List of EmployeeReponse, can be empty.
   */
  @GetMapping("/employeeByStatus/{status}")
  public ResponseEntity<List<EmployeeResponse>> getEmployeesByStatus(@PathVariable String status) {
    List<EmployeeResponse> employee = employeeService.getEmployesByStatus(status);
    return employee.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employee);
  }

  @GetMapping("/employeeByDepartment/{department}")
  public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(@PathVariable String department) {
    List<EmployeeResponse> employees = employeeService.getEmployeesByDepartment(department);
    return employees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
  }

  @PostMapping("/createEmployee")
  public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid PostEmployeeRequest postEmployeeRequest){
    Optional<EmployeeResponse> employeeResponse = employeeService.createEmployee(postEmployeeRequest);
    return employeeResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PatchMapping("/{id}/updateEmployeeStatus")
  public ResponseEntity<EmployeeResponse> updateEmployeeStatus (@PathVariable Long id ,@RequestBody @Valid UpdateEmployeeStatusRequest updateEmployeeStatusRequest){
    Optional<EmployeeResponse> employeeResponse = employeeService.updateStatusEmployee(id, updateEmployeeStatusRequest);
    return employeeResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

}
