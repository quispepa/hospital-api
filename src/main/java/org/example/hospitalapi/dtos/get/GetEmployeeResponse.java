package org.example.hospitalapi.dtos.get;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Patient;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetEmployeeResponse {
  private Long id;
  private String department;
  private String name;
  private EmployeeStatus employeeStatus;
  private List<Patient> patients;
}
