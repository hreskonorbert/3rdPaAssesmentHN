package com.codecool.web.model;

public class Subordinate {

    String firstName;
    String lastName;
    String title;
    String fullAddress;
    String birthDate;
    String hireDate;
    int numberOfTerritories;

    public Subordinate(String firstName, String lastName, String title, String fullAddress, String birthDate, String hireDate, int numberOfTerritories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.fullAddress = fullAddress;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.numberOfTerritories = numberOfTerritories;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getHireDate() {
        return hireDate;
    }

    public int getNumberOfTerritories() {
        return numberOfTerritories;
    }
}
