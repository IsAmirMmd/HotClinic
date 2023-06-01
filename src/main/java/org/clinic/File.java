package org.clinic;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;


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



}
