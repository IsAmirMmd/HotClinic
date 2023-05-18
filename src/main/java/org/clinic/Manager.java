package org.clinic;

import java.util.ArrayList;
import java.util.UUID;

public class Manager extends Person
        implements DoctorManagement, PatientManagement, PrescriptionManagement, DrugManagement {
    private String clinicName;
    public ArrayList<Patient> patients;
    public ArrayList<Doctor> doctors;
    public ArrayList<Nurse> nurses;
    public ArrayList<Drug> drugs;

    public Manager(String name, String address, String phone, String clinicName) {
        super(name, address, phone);
        this.clinicName = clinicName;
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.drugs = new ArrayList<>();
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        return doctors.add(doctor);
    }

    @Override
    public boolean removeDoctor(Doctor doctor) {
        return doctors.remove(doctor);
    }

    @Override
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public boolean addPatient(Patient patient) {
        return patients.add(patient);
    }

    @Override
    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }

    @Override
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    @Override
    public ArrayList<Patient.Prescription> getAllPrescriptions() {
        ArrayList<Patient.Prescription> allPrescriptions = new ArrayList<>();
        for (Patient patient : patients) {
            allPrescriptions.addAll(patient.getPrescriptions());
        }
        return allPrescriptions;
    }

   
}
