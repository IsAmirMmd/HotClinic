package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Manager extends Person
        implements DoctorManagement, PatientManagement, PrescriptionManagement, DrugManagement {
    private String clinicName;
    public ArrayList<Patient> patients;
    public ArrayList<Doctor> doctors;
    public ArrayList<Nurse> nurses;
    public ArrayList<Drug> drugs;
    public ArrayList<Personnel> personnels;

    public Manager(String name, String address, String phone, String clinicName, ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Nurse> nurses, ArrayList<Drug> drugs, ArrayList<Personnel> personnels) {
        super(name, address, phone);
        this.clinicName = clinicName;
        this.patients = patients;
        this.doctors = doctors;
        this.nurses = nurses;
        this.drugs = drugs;
        this.personnels = personnels;
    }


    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        File.saveTo(doctor);
        return doctors.add(doctor);
    }

    @Override
    public boolean removeDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        File.removeFrom(doctor);
        return doctors.remove(doctor);
    }

    @Override
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public boolean addPatient(Patient patient) {
        File.saveTo(patient);

        return patients.add(patient);
    }

    @Override
    public boolean removePatient(Patient patient) throws SQLException, ClassNotFoundException {
        File.removeFrom(patient);
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

    @Override
    public ArrayList<Drug> getAllDrugs() {
        return drugs;
    }

    @Override
    public boolean addDrug(Drug drug) {
        return drugs.add(drug);
    }

    @Override
    public boolean removeDrug(Drug drug) {
        return drugs.remove(drug);
    }

    public Drug getDrugByName(String name) {
        for (Drug drug : drugs) {
            if (drug.getName().equals(name)) {
                return drug;
            }
        }
        return null;
    }

    public Drug getDrugByUid(UUID uid) {
        for (Drug drug : drugs) {
            if (drug.getUid().equals(uid)) {
                return drug;
            }
        }
        return null;
    }

    public void addNurse(Nurse nurse) {
        File.saveTo(nurse);
        nurses.add(nurse);
    }

    public void removeNurse(Nurse nurse) throws SQLException, ClassNotFoundException {
        File.removeFrom(nurse);
        nurses.remove(nurse);
    }

    public void addPersonnel(Personnel personnel) {
        File.saveTo(personnel);

        personnels.add(personnel);
    }

    public void removePersonnel(Personnel personnelToRemove) throws SQLException, ClassNotFoundException {
        File.removeFrom(personnelToRemove);
        personnels.remove(personnelToRemove);
    }
}
