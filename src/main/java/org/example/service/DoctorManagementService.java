package org.example.service;

import org.example.entity.Doctor;
import org.example.entity.Slots;
import org.example.enums.Specialization;
import org.example.model.SortingModel;
import org.example.repository.InmemoryRepository;
import org.example.util.DefaultRankingStrategy;
import org.example.util.Ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorManagementService {
    InmemoryRepository inmemoryRepository;

    private static final DoctorManagementService doctorManagementService = new DoctorManagementService();
    private DoctorManagementService() {
        this.inmemoryRepository = InmemoryRepository.getInstance();
    }
    public static  DoctorManagementService getInstance() {
        return doctorManagementService;
    }
    public void registerDoctor(Doctor doctor){
        inmemoryRepository.registerDoctor(doctor);
    }

    public Map<String, List<Slots>> getAvailableDoctorsSlotsBySpecialization(Specialization specialization){
        List<String> specializationDoctors = getDoctorsBySpecialization(specialization);
        Map<String, List<Slots>> doctorsAvailability = new HashMap<>();
        for(String doctor : specializationDoctors){
            doctorsAvailability.put(doctor, getAllSlots(doctor));
        }
        return doctorsAvailability;
    }

    public List<SortingModel> findSlotsBySpecialization(Specialization specialization,Ranking ranking){
        List<SortingModel> doctorSlots = new ArrayList<>();
        Map<String, List<Slots>>  availability = getAvailableDoctorsSlotsBySpecialization(specialization);
        for(String doctor: availability.keySet()){
            for(Slots slots : availability.get(doctor)){
                SortingModel doctorSlot = new SortingModel(inmemoryRepository.getDoctorByName(doctor), slots);
                doctorSlots.add(doctorSlot);
            }

        }

        return ranking.sort(doctorSlots, true);
    }
    public List<SortingModel> findSlotsBySpecialization(Specialization specialization){
        return findSlotsBySpecialization(specialization, DefaultRankingStrategy.getInstance());
    }

    public List<String> getDoctorsBySpecialization(Specialization specialization){
        return this.inmemoryRepository.getDoctorsBySpecialization(specialization);
    }
    public List<Slots> getFreeSlotsByDoctor(String doctor){
        Doctor doctorDetails = inmemoryRepository.getDoctorByName(doctor);
        return doctorDetails.getAvailableSlots().stream().filter(slot->!slot.isBooked()).toList();
    }

    public  List<Slots> getAllSlots(String doctor){
        Doctor doctorDetails = inmemoryRepository.getDoctorByName(doctor);
        return doctorDetails.getAvailableSlots();
    }

    public Doctor getDoctorByName(String doctorName) {
        return inmemoryRepository.getDoctorByName(doctorName);
    }
}
