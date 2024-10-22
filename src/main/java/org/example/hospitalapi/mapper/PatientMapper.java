package org.example.hospitalapi.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hospitalapi.dtos.get.GetPatientResponse;
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
public class PatientMapper {
  @Autowired
  private ModelMapper modelMapper = new ModelMapper();

  public GetPatientResponse toDtoGetPatientResponse(Patient patient) {
    return modelMapper.map(patient, GetPatientResponse.class);
  }

  public List<GetPatientResponse> toDtoGetPatientsResponseList(List<Patient> patients) {
    return patients.stream().map(this::toDtoGetPatientResponse).collect(Collectors.toList());
  }

}
