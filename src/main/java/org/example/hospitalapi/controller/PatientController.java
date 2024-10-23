package org.example.hospitalapi.controller;

import jakarta.validation.Valid;
import org.example.hospitalapi.dtos.PatientResponse;
import org.example.hospitalapi.dtos.PostPatientRequest;
import org.example.hospitalapi.dtos.UpdatePatientRequest;
import org.example.hospitalapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/patients")
public class PatientController {
  @Autowired
  private PatientService patientService;

  @GetMapping("/getAllPatients")
  public ResponseEntity<List<PatientResponse>> getAllPatients() {
    List<PatientResponse> getPatientsResponse = patientService.getAllPatients();
    if (!getPatientsResponse.isEmpty()) {
      return ResponseEntity.ok(getPatientsResponse);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/getPatientById/{id}")
  public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
    Optional<PatientResponse> getPatientResponse = patientService.getPatientById(id);
    return getPatientResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Endpoint to get all patients by date of birth range from database
   *
   * @param from Date of birth range init
   * @param to   Date of birth range limit
   * @return ResponseEntity<List < GetPatientResponse>> Return a html response that content a list of GetPatientResponse
   */
  @GetMapping("/getPatientsByBirthDateRange")
  //GET /api/patients/getPatientsByBirthDateRange?from=1980-01-01&to=1990-12-31
  public ResponseEntity<List<PatientResponse>> getPatientsByBirthDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
    List<PatientResponse> getPatientResponseList = patientService.getPatientsByBirthDateRange(from, to);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @GetMapping("/getPatientsByMedicalDepartment/{medicalDepartment}")
  public ResponseEntity<List<PatientResponse>> getPatientsByMedicalDepartment(@PathVariable String medicalDepartment) {
    List<PatientResponse> getPatientResponseList = patientService.getPatientsByMedicalDepartment(medicalDepartment);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @GetMapping("/getPatientsByMedicalStatus/{status}") //status OFF = 2
  public ResponseEntity<List<PatientResponse>> getPatientsByMedicalStatus(@PathVariable int status) {
    List<PatientResponse> getPatientResponseList = patientService.getPatientsByMedicalStatus(status);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @PostMapping("/createPatient")
  public ResponseEntity<PatientResponse> createPatient(@RequestBody @Valid PostPatientRequest postPatientRequest) {
    Optional<PatientResponse> postPatientResponse = patientService.createPatient(postPatientRequest);
    return postPatientResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/updatePatient/{id}")
  public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest){
    Optional<PatientResponse> updatePatientResponse = patientService.updatePatient(id, updatePatientRequest);
    return updatePatientResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
