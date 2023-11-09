package com.example.smarkelectronics.Model;

public class SelectAddressModel {
    private String name;
    private String address;
    private String phonenumber;
    private String city;
    private String district;
    private String ward;

    public SelectAddressModel(String name, String address, String phonenumber, String city, String district, String ward) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public SelectAddressModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
