package org.example.hospitalapi.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostEmployeeRequest {
  @NotBlank
  private String department;
  @NotBlank
  private String name;
  @NotBlank
  private String employeeStatus;
}
