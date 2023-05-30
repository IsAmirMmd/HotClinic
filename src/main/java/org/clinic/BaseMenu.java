package org.clinic;

import javax.print.Doc;
import java.util.Scanner;
import java.util.ArrayList;


public class BaseMenu {
    public static int num;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Patient> patients = new ArrayList<>();
    public static ArrayList<Doctor> doctors = new ArrayList<>();
    public static ArrayList<Nurse> nurses = new ArrayList<>();
    public static ArrayList<Drug> drugs = new ArrayList<>();
    public static Manager manager = new Manager("amirMmd", null, null, "N.I.T");


    public static void baseMenu() {
        System.out.println("1. manager panel");
        System.out.println("2. patient panel");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                managerLogin();
                break;
            case 2:
                patientPanel();
                break;
        }
    }

    public static void managerLogin() {
        clearConsole();
        boolean IsAllowed = false;
        Scanner admin = new Scanner(System.in);
        System.out.println("Enter Admin UserName:");
        String userName = admin.nextLine();
        System.out.println("Enter Admin passWord:");
        String passWord = admin.nextLine();
        if (userName.equals("admin") && passWord.equals("admin")) {
            System.out.println("you enter data correctly");
            managerPanel(manager);
        }
    }

    public static void managerPanel(Manager manager) {
        clearConsole();
        System.out.println("**** welcome manager ****");
        System.out.println("select action from menu :");
        System.out.println("1. manage doctors");
        System.out.println("2. manage nurses");
        System.out.println("3. manage patient");
        System.out.println("4. manage drugs");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                manageDoctor();
                break;

            case 2:
                manageNurses();
                break;

            case 3:
                managePatient();
                break;

            case 4:
                manageDrugs();
                break;
        }
    }

    public static void manageDoctor() {
        clearConsole();
        System.out.println("**** Doctor menu ****");
        System.out.println("1. Add Doctor");
        System.out.println("2. Remove Doctor");
        System.out.println("3. Show All Doctors");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addDoctor();
                break;

            case 2:
                removeDoctor();
                break;

            case 3:
                showAllDoctor();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addDoctor() {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter doctor name: ");
        String name = add.nextLine();

        System.out.println("Enter doctor address: ");
        String address = add.nextLine();

        System.out.println("Enter doctor phone: ");
        String phone = add.nextLine();

        System.out.println("Enter doctor speciality: ");
        String speciality = add.nextLine();

        Doctor doctor = new Doctor(name, address, phone, speciality);
        doctors.add(doctor);
        manager.addDoctor(doctor);

        System.out.println("new doctor with name " + name + " added!");
        sleepTime(2000);
        manageDoctor();
    }

    public static void removeDoctor() {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter doctor name: ");
        String name = remove.nextLine();

        Doctor doctorToRemove = null;
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                doctorToRemove = doctor;
                break;
            }
        }

        if (doctorToRemove == null) {
            System.out.println("Doctor not found.");
        }
        doctors.remove(doctorToRemove);
        manager.removeDoctor(doctorToRemove);

        System.out.println("the doctor with name " + name + " removed!");
        sleepTime(2000);
        manageDoctor();
    }

    public static void showAllDoctor() {
        clearConsole();
        System.out.println("showing all doctors...");
        for (Doctor doctor : doctors) {
            System.out.println("ID         :  " + doctor.getId());
            System.out.println("name       :  " + doctor.getName());
            System.out.println("address    :  " + doctor.getAddress());
            System.out.println("phone      :  " + doctor.getPhone());
            System.out.println("speciality :  " + doctor.getSpecialty());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageDoctor();
                break;
        }
    }

    public static void manageNurses() {
        clearConsole();
        System.out.println("**** Nurse menu ****");
        System.out.println("1. Add Nurse");
        System.out.println("2. Remove Nurse");
        System.out.println("3. Show All Nurses");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addNurse();
                break;

            case 2:
                removeNurse();
                break;

            case 3:
                showAllNurse();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addNurse() {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter nurse name: ");
        String name = add.nextLine();

        System.out.println("Enter nurse address: ");
        String address = add.nextLine();

        System.out.println("Enter nurse phone: ");
        String phone = add.nextLine();

        Nurse nurse = new Nurse(name, address, phone);
        nurses.add(nurse);
        manager.addNurse(nurse);

        System.out.println("new nurse with name " + name + " added!");
        sleepTime(2500);
        manageNurses();
    }

    public static void removeNurse() {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter nurse name: ");
        String name = remove.nextLine();

        Nurse nurseToRemove = null;
        for (Nurse nurse : nurses) {
            if (nurse.getName().equals(name)) {
                nurseToRemove = nurse;
                break;
            }
        }

        if (nurseToRemove == null) {
            System.out.println("Nurse not found.");
        } else {

            nurses.remove(nurseToRemove);
            manager.removeNurse(nurseToRemove);
            System.out.println("the nurse with name " + name + " removed!");
        }
        sleepTime(2500);
        manageNurses();
    }

    public static void showAllNurse() {
        clearConsole();
        System.out.println("showing all nurses...");
        for (Nurse nurse : nurses) {
            System.out.println("ID         :  " + nurse.getId());
            System.out.println("name       :  " + nurse.getName());
            System.out.println("address    :  " + nurse.getAddress());
            System.out.println("phone      :  " + nurse.getPhone());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageNurses();
                break;
        }
    }


    public static void managePatient() {
        clearConsole();
        System.out.println("**** patients menu ****");
        System.out.println("1. Add Patients");
        System.out.println("2. Remove Patient");
        System.out.println("3. Show All Patients");
        System.out.println("0. Back");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                addPatients();
                break;

            case 2:
                removePatients();
                break;

            case 3:
                showAllPatients();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addPatients() {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String name = add.nextLine();

        System.out.println("Enter patient address: ");
        String address = add.nextLine();

        System.out.println("Enter patient phone: ");
        String phone = add.nextLine();

        Patient patient = new Patient(name, address, phone, null);
        patients.add(patient);
        manager.addPatient(patient);

        System.out.println("new patient with name " + name + " added!");
        sleepTime(2500);
        managePatient();
    }

    public static void removePatients() {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter patient name: ");
        String name = remove.nextLine();

        Patient patientToRemove = null;
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patientToRemove = patient;
                break;
            }
        }

        if (patientToRemove == null) {
            System.out.println("Patient not found.");
        }
        patients.remove(patientToRemove);
        manager.removePatient(patientToRemove);

        System.out.println("the patient with name " + name + " removed!");
        sleepTime(2000);
        managePatient();
    }


    public static void showAllPatients() {
        clearConsole();
        System.out.println("showing all patients...");
        for (Patient patient : patients) {
            System.out.println("ID         :  " + patient.getId());
            System.out.println("name       :  " + patient.getName());
            System.out.println("address    :  " + patient.getAddress());
            System.out.println("phone      :  " + patient.getPhone());
            System.out.println("illness    :  " + patient.getIllness());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                managePatient();
                break;
        }
    }


    public static void manageDrugs() {
        clearConsole();
        System.out.println("**** DrugStore menu ****");
        System.out.println("1. Add Drugs");
        System.out.println("2. Remove Drugs");
        System.out.println("3. Show All Drugs");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addDrug();
                break;

            case 2:
                removeDrug();
                break;

            case 3:
                showAllDrugs();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addDrug() {
        clearConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the drug: ");
        String name = scanner.nextLine();
        System.out.print("Enter the quantity of the drug: ");
        int quantity = scanner.nextInt();

        Drug drug = new Drug(name, quantity);

        drugs.add(drug);
        manager.addDrug(drug);

        System.out.println("new drug with name " + name + " added!");
        sleepTime(2500);
        manageDrugs();
    }

    public static void removeDrug() {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter drug name: ");
        String name = remove.nextLine();

        Drug drugToRemove = null;
        for (Drug drug : drugs) {
            if (drug.getName().equals(name)) {
                drugToRemove = drug;
                break;
            }
        }

        if (drugToRemove == null) {
            System.out.println("Drug not found.");
        }
        drugs.remove(drugToRemove);
        manager.removeDrug(drugToRemove);

        System.out.println("the drug with name " + name + " removed!");
        sleepTime(2000);
        manageDrugs();
    }

    public static void showAllDrugs() {
        clearConsole();
        System.out.println("showing all drugs...");
        for (Drug drug : drugs) {
            System.out.println("ID         :  " + drug.getUid());
            System.out.println("name       :  " + drug.getName());
            System.out.println("quantity    :  " + drug.getQuantity());
            System.out.println("availability      :  " + drug.isAvailable());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageDrugs();
                break;
        }
    }

    public static void patientPanel() {
    }

    public static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        for (int line = 0; line < 5; line++) {
            System.out.println("\n");
        }
    }
}
