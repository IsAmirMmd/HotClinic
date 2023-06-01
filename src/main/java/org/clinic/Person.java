package org.clinic;

public class Person {
    private static long counter = 0;
    private long id;
    private String name;
    private String address;
    private String phone;
    private long salary;

    public Person(String name, String address, String phone) {
        this.id = ++counter;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Person(String name, String address, String phone, long salary) {
        this.id = ++counter;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Address: " + getAddress() + ", Phone: " + getPhone();
    }
}
