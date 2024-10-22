package org.example.hospitalapi.service;

import org.example.hospitalapi.dtos.get.GetPatientResponse;
import org.example.hospitalapi.dtos.post.PostPatientRequest;
import org.example.hospitalapi.dtos.post.PostPatientResponse;
import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.mapper.PatientMapper;
import org.example.hospitalapi.model.Patient;
import org.example.hospitalapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class PatientService {
  @Autowired
  private PatientRepository patientRepository;

  private final PatientMapper patientMapper = new PatientMapper();

  /*
  Get methods
   */

  public List<GetPatientResponse> getAllPatients() {
    List<Patient> patients = patientRepository.findAll();
    return patientMapper.toDtoGetPatientsResponseList(patients);
  }

  public Optional<GetPatientResponse> getPatientById(Long id) {
    Optional<Patient> patient = patientRepository.findById(id);
    return patient.map(patientMapper::toDtoGetPatientResponse);
  }

  public List<GetPatientResponse> getPatientsByBirthDateRange(LocalDate from, LocalDate to) {
    List<Patient> patients = patientRepository.findPatientsByDateOfBirthBetween(from, to);
    return patientMapper.toDtoGetPatientsResponseList(patients);
  }

  public List<GetPatientResponse> getPatientsByMedicalDepartment(String medicalDepartment) {
    List<Patient> patients = patientRepository.findPatientsByAdmittedBy_Department(medicalDepartment);
    return patientMapper.toDtoGetPatientsResponseList(patients);
  }

  public List<GetPatientResponse> getPatientsByMedicalStatus(Integer employeeStatus) {
    List<Patient> patients = (employeeStatus == 0) ? patientRepository.findPatientsByAdmittedByEmployeeStatus(EmployeeStatus.ON_CALL) : (employeeStatus == 1) ? patientRepository.findPatientsByAdmittedByEmployeeStatus(EmployeeStatus.ON) : (employeeStatus == 2) ? patientRepository.findPatientsByAdmittedByEmployeeStatus(EmployeeStatus.ON) : null;
    return patients != null ? patientMapper.toDtoGetPatientsResponseList(patients) : null;
  }

  /*
  Post methods
   */
  public Optional<PostPatientResponse> createPatient(PostPatientRequest postPatientRequest) {
    //Employee employeeId = (postPatientRequest.getEmployeeId());
    System.out.println("Test " + postPatientRequest);

    return null;

  }
}
