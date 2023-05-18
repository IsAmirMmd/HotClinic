package org.clinic;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person{
    private ArrayList<Patient> patients;

    public Nurse(String name,String address,String phone) {
        super(name,address,phone);
        this.patients = new ArrayList<>();
    }


    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }

    public List<Patient.Prescription> getPatientPrescriptions(Patient patient) {
        if (patients.contains(patient)) {
            return patient.getPrescriptions();
        }
        return null;
    }
}
