package org.example.hospitalapi;

import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Employee;
import org.example.hospitalapi.model.Patient;
import org.example.hospitalapi.repository.EmployeeRepository;
import org.example.hospitalapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {
  @Autowired
  private EmployeeRepository employeesRepository;
  @Autowired
  private PatientRepository patientRepository;
  @Override
  public void run(String... args) throws Exception {

  }

  public void saveData(){
    Employee employee1 = new Employee("cardiology" ,"Alonso Flores", EmployeeStatus.ON_CALL);
    Employee employee2 = new Employee("immunology" ,"Sam Ortega", EmployeeStatus.ON);
    Employee employee3 = new Employee("cardiology" ,"German Ruiz", EmployeeStatus.OFF);
    Employee employee4 = new Employee("pulmonary" ,"Maria Lin", EmployeeStatus.ON);
    Employee employee5 = new Employee("orthopaedic" ,"Paolo Rodriguez", EmployeeStatus.ON_CALL);
    Employee employee6 = new Employee("psychiatric" ,"John Paul Armes", EmployeeStatus.OFF);
    employeesRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));

    Patient patient1 = new Patient(null, "Jaime Jordan", LocalDate.of(1984, 3, 2), employee2);
    Patient patient2 = new Patient(null, "Marian Garcia", LocalDate.of(1972, 1, 12), employee2);
    Patient patient3 = new Patient(null, "Julia Dusterdieck", LocalDate.of(1954, 6, 11), employee1);
    Patient patient4 = new Patient(null, "Steve McDuck", LocalDate.of(1931, 11, 10), employee3);
    Patient patient5 = new Patient(null, "Marian Garcia", LocalDate.of(1999, 2, 15), employee6);
    patientRepository.saveAll(Arrays.asList(patient1, patient2, patient3, patient4, patient5));

    employee2.setPatients(List.of(patient1, patient2));
    employee1.setPatients(List.of(patient3));
    employee3.setPatients(List.of(patient4));
    employee6.setPatients(List.of(patient5));

    employeesRepository.saveAll(List.of(employee1, employee2, employee3, employee4, employee5, employee6));
  }
}
