package org.example.hospitalapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hospitalapi.dtos.EmployeeResponse;
import org.example.hospitalapi.dtos.PostEmployeeRequest;
import org.example.hospitalapi.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeMapper {
  @Autowired
  private ModelMapper modelMapper = new ModelMapper();

  public EmployeeResponse fromEmployeeToDtoEmployeeResponse(Employee employee) {
    return modelMapper.map(employee, EmployeeResponse.class);
  }

  public List<EmployeeResponse> fromEmployeeListToDtoGetEmployeeResponseList(List<Employee> employees) {
    return employees.stream().map(this::fromEmployeeToDtoEmployeeResponse).collect(Collectors.toList());
  }

  public Employee fromDtoEmployeeResponseToEmployee(EmployeeResponse employeeResponse) {
    return modelMapper.map(employeeResponse, Employee.class);
  }

  public Employee fromDtoPostEmployeeRequestToEmployee(PostEmployeeRequest postEmployeeRequest) {
    return modelMapper.map(postEmployeeRequest, Employee.class);
  }
}
