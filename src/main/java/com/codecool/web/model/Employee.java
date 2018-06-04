package com.codecool.web.model;

public class Employee extends User{

    String lastName;
    String firstName;
    String title;

    public Employee(int id, String lastName, String firstName, String title) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTitle() {
        return title;
    }
}
