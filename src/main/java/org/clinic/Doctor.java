package org.clinic;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.ArrayList;

public class Doctor extends Person {
    private String specialty;
    private ArrayList<Patient> patients;

    public Doctor(String name, String address, String phone, String specialty, long salary) {
        super(name, address, phone, salary);
        this.specialty = specialty;
        this.patients = new ArrayList<>();
    }

    public String getSpecialty() {
        return specialty;
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

    public ArrayList<Patient.Prescription> getPatientPrescriptions(Patient patient) {
        if (patients.contains(patient)) {
            return patient.getPrescriptions();
        }
        return null;
    }

    public Patient.Prescription writePrescription(Patient patient, ArrayList<Drug> medication) throws SQLException, ClassNotFoundException {
        String drug1 = "null";
        String drug2 = "null";
        int i = 0;
        if (patients.contains(patient)) {
            for (Drug drug : medication) {
                if (drug.getQuantity() == 0) {
                    medication.remove(drug);
                } else {
                    if (i == 0) {
                        drug1 = drug.getName();
                    } else {
                        drug2 = drug.getName();
                    }
                    drug.setQuantity(drug.getQuantity() - 1);
                    File.updateDrug(drug);
                }
                i++;
            }
            Patient.Prescription prescription = new Patient.Prescription(new java.util.Date(), drug1, drug2, this.getId(), patient.getId());
            File.writePrescription(patient, prescription);
            for (Drug drug : medication) {
                patient.addMedication(drug.getName());
            }
            patient.addPrescription(prescription);
            return prescription;
        }
        return null;
    }

    public static ArrayList<Doctor> firstLoad() {
        ArrayList<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor("Dr. Ali Mohammadi", "Tehran, Valiasr Street", "09123456789", "Headache", 1500000));
        doctors.add(new Doctor("Dr. Sara Rahimi", "Isfahan, Chaharbagh Street", "09123456789", "Anemia", 1500000));
        doctors.add(new Doctor("Dr. Mohammad Hosseini", "Shiraz, Hafez Street", "09123456789", "Allergy", 1500000));
        doctors.add(new Doctor("Dr. Maryam Farahani", "Tabriz, Saat Square", "09123456789", "High blood pressure", 1500000));
        doctors.add(new Doctor("Dr. Hossein Zarei", "Mashhad, Imam Reza Street", "09123456789", "Sinusitis", 1500000));
        doctors.add(new Doctor("Dr. Zahra Gholami", "Tehran, Enghelab Street", "09123456789", "Diabetes", 1500000));

        return doctors;
    }

}
