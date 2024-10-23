package org.example.hospitalapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostPatientRequest {
  @NotBlank
  private String name;
  @Past
  private LocalDate dateOfBirth;
  @PositiveOrZero
  @NotNull
    private Long employee;
}
