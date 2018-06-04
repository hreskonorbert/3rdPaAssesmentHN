package com.codecool.web.model;

public class Supplier extends User {

    String contactName;
    String companyName;
    String contactTitle;


    public Supplier(int id, String contactName, String companyName, String contactTitle) {
        super(id);
        this.contactName = contactName;
        this.companyName = companyName;
        this.contactTitle = contactTitle;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactTitle() {
        return contactTitle;
    }
}
