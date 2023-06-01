package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;

interface DoctorManagement {
    boolean addDoctor(Doctor doctor);
    boolean removeDoctor(Doctor doctor) throws SQLException, ClassNotFoundException;
    ArrayList<Doctor> getDoctors();
}
