package com.example.smarkelectronics.Model;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private int idaddress;
    private String nameaddress;
    private String chitiet;
    private String Phuong;
    private String Quan;
    private String thanhpho;
    private String phoneaddress;

    public AddressModel(int idaddress, String nameaddress, String chitiet, String phuong, String quan, String thanhpho, String phoneaddress) {
        this.idaddress = idaddress;
        this.nameaddress = nameaddress;
        this.chitiet = chitiet;
        Phuong = phuong;
        Quan = quan;
        this.thanhpho = thanhpho;
        this.phoneaddress = phoneaddress;
    }

    public int getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(int idaddress) {
        this.idaddress = idaddress;
    }

    public String getNameaddress() {
        return nameaddress;
    }

    public void setNameaddress(String nameaddress) {
        this.nameaddress = nameaddress;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getPhuong() {
        return Phuong;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public String getQuan() {
        return Quan;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getPhoneaddress() {
        return phoneaddress;
    }

    public void setPhoneaddress(String phoneaddress) {
        this.phoneaddress = phoneaddress;
    }
}
