package org.example.hospitalapi.dtos;

import lombok.*;
import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Patient;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeResponse {
  private Long id;
  private String department;
  private String name;
  private EmployeeStatus employeeStatus;
  private List<Patient> patients;
}
