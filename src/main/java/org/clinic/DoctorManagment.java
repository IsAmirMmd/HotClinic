package org.clinic;

import java.util.ArrayList;

interface DoctorManagement {
    boolean addDoctor(Doctor doctor);
    boolean removeDoctor(Doctor doctor);
    ArrayList<Doctor> getDoctors();
}
