package com.example.smarkelectronics.Model;

public class Customer {
    int idcustomer;
    String namecustomer;
    String email;
    String passwordcustomer;

    public Customer(int idcustomer, String namecustomer, String email, String passwordcustomer) {
        this.idcustomer = idcustomer;
        this.namecustomer = namecustomer;
        this.email = email;
        this.passwordcustomer = passwordcustomer;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getNamecustomer() {
        return namecustomer;
    }

    public void setNamecustomer(String namecustomer) {
        this.namecustomer = namecustomer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordcustomer() {
        return passwordcustomer;
    }

    public void setPasswordcustomer(String passwordcustomer) {
        this.passwordcustomer = passwordcustomer;
    }
}
