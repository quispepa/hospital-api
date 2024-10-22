package org.example.hospitalapi.controller;

import jakarta.validation.Valid;
import org.example.hospitalapi.dtos.get.GetPatientResponse;
import org.example.hospitalapi.dtos.post.PostPatientRequest;
import org.example.hospitalapi.dtos.post.PostPatientResponse;
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
  public ResponseEntity<List<GetPatientResponse>> getAllPatients() {
    List<GetPatientResponse> getPatientsResponse = patientService.getAllPatients();
    if (!getPatientsResponse.isEmpty()) {
      return ResponseEntity.ok(getPatientsResponse);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/getPatientById/{id}")
  public ResponseEntity<GetPatientResponse> getPatientById(@PathVariable Long id) {
    Optional<GetPatientResponse> getPatientResponse = patientService.getPatientById(id);
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
  public ResponseEntity<List<GetPatientResponse>> getPatientsByBirthDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
    List<GetPatientResponse> getPatientResponseList = patientService.getPatientsByBirthDateRange(from, to);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @GetMapping("/getPatientsByMedicalDepartment/{medicalDepartment}")
  public ResponseEntity<List<GetPatientResponse>> getPatientsByMedicalDepartment(@PathVariable String medicalDepartment) {
    List<GetPatientResponse> getPatientResponseList = patientService.getPatientsByMedicalDepartment(medicalDepartment);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @GetMapping("/getPatientsByMedicalStatus/{status}") //status OFF = 2
  public ResponseEntity<List<GetPatientResponse>> getPatientsByMedicalStatus(@PathVariable int status) {
    List<GetPatientResponse> getPatientResponseList = patientService.getPatientsByMedicalStatus(status);
    return getPatientResponseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(getPatientResponseList);
  }

  @PostMapping("/createPatient")
  public ResponseEntity<PostPatientResponse> createPatient(@RequestBody @Valid PostPatientRequest postPatientRequest) {
    Optional<PostPatientResponse> postPatientResponse = patientService.createPatient(postPatientRequest);
    return postPatientResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
