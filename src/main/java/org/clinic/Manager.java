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

}
