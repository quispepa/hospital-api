package org.example.hospitalapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalapi.enums.EmployeeStatus;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
public class Employee {
  @Id
  @Column(name = "employee_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "employee_department")
  private String department;
  @Column(name = "employee_name")
  private String name;
  @Column(name = "employee_status")
  @Enumerated(EnumType.STRING)
  private EmployeeStatus employeeStatus;
  @OneToMany(mappedBy = "admittedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Patient> patients;

  public Employee(String department, String name, EmployeeStatus employeeStatus) {
    this.id = null;
    this.department = department;
    this.name = name;
    this.employeeStatus = employeeStatus;
    this.patients = new ArrayList<>();
  }
}
