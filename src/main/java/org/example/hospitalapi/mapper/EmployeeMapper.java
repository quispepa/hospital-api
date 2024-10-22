package org.example.hospitalapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hospitalapi.dtos.get.GetEmployeeResponse;
import org.example.hospitalapi.dtos.get.GetPatientResponse;
import org.example.hospitalapi.model.Employee;
import org.example.hospitalapi.model.Patient;
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

  public GetEmployeeResponse toDtoGetEmployeeResponse(Employee employee) {
    return modelMapper.map(employee, GetEmployeeResponse.class);
  }

  public List<GetEmployeeResponse> toDtoGetEmployeesResponseList(List<Employee> employees) {
    return employees.stream().map(this::toDtoGetEmployeeResponse).collect(Collectors.toList());
  }
}
