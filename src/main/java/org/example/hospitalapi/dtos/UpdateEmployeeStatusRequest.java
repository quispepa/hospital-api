package org.example.hospitalapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateEmployeeStatusRequest {
  @NotBlank
  private String employeeStatus;

}
