package org.example.hospitalapi.dtos;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatePatientRequest {
  private String name;
  private LocalDate dateOfBirth;
  private Long employee;
}
