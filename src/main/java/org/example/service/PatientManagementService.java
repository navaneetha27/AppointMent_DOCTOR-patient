package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Patient;
import org.example.repository.InmemoryRepository;

import java.util.Collection;
import java.util.List;

public class PatientManagementService {
    private static  PatientManagementService patientManagementService = new PatientManagementService();
    InmemoryRepository inmemoryRepository;

    private PatientManagementService() {
        this.inmemoryRepository = InmemoryRepository.getInstance();
    }

    public static PatientManagementService getInstance() {
        return patientManagementService;
    }
    public List<String> getAllPatients(){
       return inmemoryRepository.getAllPatient();
    }
    public Collection<String> getPatientAppointMents(String patientName){
        Patient patient = inmemoryRepository.getPatientByName(patientName);
        return patient.getAppointMents();
    }

    public Patient getPatient(String patientName) {
        return  inmemoryRepository.getPatientByName(patientName);
    }
    public void registerPatient(Patient patient){
        inmemoryRepository.registerPatient(patient);
    }

}
