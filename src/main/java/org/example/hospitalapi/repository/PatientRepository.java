package org.example.hospitalapi.repository;

import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  List<Patient> findAll();
  List<Patient> findPatientsByDateOfBirthBetween(LocalDate from, LocalDate to);
  List<Patient> findPatientsByAdmittedBy_Department(String department);
  List<Patient> findPatientsByAdmittedByEmployeeStatus(EmployeeStatus employeeStatus);
}
