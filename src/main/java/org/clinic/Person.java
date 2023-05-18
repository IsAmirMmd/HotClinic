package org.clinic;

public class Person {
    private static long counter = 0;
    private long id;
    private String name;
    private String address;
    private String phone;

    public Person(String name, String address, String phone) {
        this.id = ++counter;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Name: " + getName() + ", Address: " + getAddress() + ", Phone: " + getPhone();
    }
}
