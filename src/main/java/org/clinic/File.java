package org.clinic;

import javax.print.Doc;
import java.awt.image.ConvolveOp;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;


public class File {
    public static String url = "jdbc:mysql://localhost:3306/hotclinic";
    public static String username = "root";
    public static String password = "";
    public static Connection conn;

    public static void saveTo(Person person) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            if (person instanceof Nurse) {
                String sql = "INSERT INTO `nurse`(`id`, `name`, `address`, `number`, `salary`) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, person.getId());
                stmt.setString(2, person.getName());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getPhone());
                stmt.setLong(5, person.getSalary());
                stmt.executeUpdate();
                stmt.close();
            } else if (person instanceof Doctor) {
                String sql = "INSERT INTO `doctor`(`id`, `name`, `address`, `number`,`speciality`, `salary`) VALUES (?,?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, person.getId());
                stmt.setString(2, person.getName());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getPhone());
                stmt.setString(5, ((Doctor) person).getSpecialty());
                stmt.setLong(6, person.getSalary());
                stmt.executeUpdate();
                stmt.close();
            } else if (person instanceof Patient) {
                String sql = "INSERT INTO `patient` (`id`, `name`, `address`, `numebr`,`illness`) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, person.getId());
                stmt.setString(2, person.getName());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getPhone());
                stmt.setString(5, "null");
                stmt.executeUpdate();
                stmt.close();
            } else if (person instanceof Personnel) {
                String sql = "INSERT INTO `personnel`(`id`, `name`, `address`, `number`,`salary`,`task`) VALUES (?,?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, person.getId());
                stmt.setString(2, person.getName());
                stmt.setString(3, person.getAddress());
                stmt.setString(4, person.getPhone());
                stmt.setLong(5, person.getSalary());
                stmt.setString(6, ((Personnel) person).getTask());
                stmt.executeUpdate();
                stmt.close();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static ArrayList<Nurse> LoadNurse() throws SQLException, ClassNotFoundException {
        ArrayList<Nurse> nurses = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM nurse";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            long salary = rs.getLong(5);
            Nurse nurse = new Nurse(name, address, phone, salary);
            nurse.setId(id);
            nurses.add(nurse);
        }
        rs.close();
        pstmt.close();

        return nurses;
    }

    public static ArrayList<Doctor> LoadDoctor() throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM doctor";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String speciality = rs.getString(5);
            long salary = rs.getLong(6);
            Doctor doctor = new Doctor(name, address, phone, speciality, salary);
            doctor.setId(id);
            doctors.add(doctor);
        }
        rs.close();
        pstmt.close();

        return doctors;
    }

    public static ArrayList<Patient> LoadPatient() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM patient";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String illness = rs.getString(5);
            Patient patient = new Patient(name, address, phone, illness);
            patient.setId(id);
            patients.add(patient);
        }
        rs.close();
        pstmt.close();

        return patients;
    }

    public static ArrayList<Personnel> LoadPersonnel() throws SQLException, ClassNotFoundException {
        ArrayList<Personnel> personnels = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM personnel";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            long Salary = rs.getLong(5);
            String task = rs.getString(6);
            Personnel personnel = new Personnel(name, address, phone, Salary, task);
            personnel.setId(id);
            personnels.add(personnel);
        }
        rs.close();
        pstmt.close();

        return personnels;
    }

    public static void removeFrom(Person person) throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(url, username, password);
        if (person instanceof Nurse) {
            String deleteSql = "DELETE FROM `nurse` WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, person.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } else if (person instanceof Personnel) {

            String deleteSql = "DELETE FROM `personnel` WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, person.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } else if (person instanceof Doctor) {

            String deleteSql = "DELETE FROM `doctor` WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, person.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } else if (person instanceof Patient) {

            String deleteSql = "DELETE FROM `patient` WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, person.getId());
            pstmt.executeUpdate();
            pstmt.close();

        }
        conn.close();
    }

    public static void updateIllness(Patient patient, String illness) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "UPDATE patient SET illness=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, illness);
        statement.setLong(2, patient.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public static void writePrescription(Patient patient, Patient.Prescription prescription) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO `prescription`(`id`, `date`, `patient_id`, `doctor_id`, `drug1` , `drug2`) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, prescription.getID());
        stmt.setString(2, prescription.getDate());
        stmt.setLong(3, prescription.getPatientId());
        stmt.setLong(4, prescription.getDoctor());
        stmt.setString(5, prescription.drug1);
        stmt.setString(6, prescription.drug2);
        //        close
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public static ArrayList<Patient.Prescription> LoadPrescription() throws SQLException, ClassNotFoundException {
        ArrayList<Patient.Prescription> prescriptions = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String query = "SELECT * FROM prescription";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String dates = rs.getString(2);
            long pa_id = rs.getLong(3);
            long doc_id = rs.getLong(4);
            String drug1 = rs.getString(5);
            String drug2 = rs.getString(6);
            String dateString = dates;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = new Date();
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateFormat);
                date = Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
            Patient.Prescription prescription = new Patient.Prescription(date, drug1, drug2, doc_id, pa_id);
            prescription.setID(id);
            prescriptions.add(prescription);
        }
        rs.close();
        pstmt.close();
        connection.close();
        return prescriptions;
    }

    public static ArrayList<String> LoadDrug(Patient patient) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        ArrayList<String> DrugList = new ArrayList<>();
        String query = "SELECT * FROM patient";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        String[] splitedDrug = null;
        while (rs.next()) {
            if (rs.getLong(1) == patient.getId()) {
                String Drugs = rs.getString(6);
                if (Drugs != null) {
                    splitedDrug = Drugs.split(",");
                    DrugList = new ArrayList<>(Arrays.asList(splitedDrug));
                }
            }
        }
        rs.close();
        pstmt.close();

        return DrugList;
    }

    public static void saveDrug(Patient patient, ArrayList<String> drugs) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "UPDATE patient SET drugs=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        String finalDrug = String.join(",", drugs);
        statement.setString(1, finalDrug);
        statement.setLong(2, patient.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public static void saveToDrug(Drug drug) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO `drug`(`id`, `name`, `quantity`, `available`) VALUES (?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, drug.getUid());
        stmt.setString(2, drug.getName());
        stmt.setInt(3, drug.getQuantity());
        stmt.setBoolean(4, drug.isAvailable());
        stmt.executeUpdate();
        stmt.close();
        connection.close();


    }

    public static ArrayList<Drug> LoadFromDrug() throws SQLException, ClassNotFoundException {
        ArrayList<Drug> drugs = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM drug";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            boolean available = rs.getBoolean(4);
            Drug drug = new Drug(name, quantity);
            drug.setAvailable(available);
            drug.setUid(id);
            drugs.add(drug);
        }
        rs.close();
        pstmt.close();
        connection.close();
        return drugs;
    }

    public static void updateDrug(Drug drug) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "UPDATE drug SET quantity=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, drug.getQuantity());
        statement.setLong(2, drug.getUid());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public static void removeDrug(Drug drug) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        String deleteSql = "DELETE FROM `drug` WHERE id = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(deleteSql);
        pstmt.setLong(1, drug.getUid());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public static void saveToDraft(Doctor doctor) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO `draftdoctor`(`id`, `name`, `address`, `number`, `speciality`, `salary`) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setLong(1, doctor.getId());
        stmt.setString(2, doctor.getName());
        stmt.setString(3, doctor.getAddress());
        stmt.setString(4, doctor.getPhone());
        stmt.setString(5, doctor.getSpecialty());
        stmt.setInt(6, 0);
        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }

    public static ArrayList<Doctor> LoadDoctors() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        ArrayList<Doctor> doctors = new ArrayList<>();

        String query = "SELECT * FROM draftdoctor";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String speciality = rs.getString(5);
            long salary = rs.getLong(6);
            Doctor doctor = new Doctor(name, address, phone, speciality, salary);
            doctor.setId(id);
            doctors.add(doctor);
        }
        rs.close();
        pstmt.close();
        connection.close();

        return doctors;
    }

    public static void removeDraft(Doctor doctor) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        String deleteSql = "DELETE FROM `draftdoctor` WHERE id = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(deleteSql);
        pstmt.setLong(1, doctor.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public static void saveToDraft(Patient.Prescription pre) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO `draftpre`(`id`, `date`, `doctor_id`, `patient_id`) VALUES (?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setLong(1, pre.getID());
        stmt.setString(2, pre.getDate());
        stmt.setLong(3, pre.getDoctor());
        stmt.setLong(4, pre.getPatientId());
        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }

    public static ArrayList<Patient.Prescription> LoadPre() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        ArrayList<Patient.Prescription> prescriptions = new ArrayList<>();

        String query = "SELECT * FROM draftpre";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            long id = rs.getLong(1);
            String dates = rs.getString(2);
            long doc_id = rs.getLong(3);
            long pa_id = rs.getLong(4);
            String dateString = dates;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = new Date();
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateFormat);
                date = Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
            Patient.Prescription prescription = new Patient.Prescription(date, "drug1", "drug2", doc_id, pa_id);
            prescription.setID(id);
            prescriptions.add(prescription);
        }
        rs.close();
        pstmt.close();
        connection.close();

        return prescriptions;
    }

    public static void removePreDraft(Patient.Prescription prescription) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        String deleteSql = "DELETE FROM `draftpre` WHERE id = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(deleteSql);
        pstmt.setLong(1, prescription.getID());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }


}
