package org.example.hospitalapi.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPatientResponse {
  private Long id;
  private String name;
  private LocalDate dateOfBirth;
}
