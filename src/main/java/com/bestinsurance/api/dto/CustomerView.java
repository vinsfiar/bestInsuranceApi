package com.bestinsurance.api.dto;

public class CustomerView {
    private String id;
    private String name;
    private String surname;
    private String email;
    private AddressView address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressView getAddress() {
        return address;
    }

    public void setAddress(AddressView address) {
        this.address = address;
    }
}
