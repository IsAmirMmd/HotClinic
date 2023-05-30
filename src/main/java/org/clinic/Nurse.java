package org.clinic;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {
    private ArrayList<Patient> patients;

    public Nurse(String name, String address, String phone) {
        super(name, address, phone);
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

    public static ArrayList<Nurse> firstLoad() {
        ArrayList<Nurse> nurses = new ArrayList<>();

        nurses.add(new Nurse("Maryam Ebrahimi", "Tehran, Valiasr Street", "09123456789"));
        nurses.add(new Nurse("Ali Mohammadi", "Isfahan, Chaharbagh Street", "09123456789"));
        nurses.add(new Nurse("Sara Hosseini", "Shiraz, Hafez Street", "09123456789"));
        nurses.add(new Nurse("Hossein Ahmadi", "Tabriz, Saat Square", "09123456789"));

        return nurses;
    }
}
