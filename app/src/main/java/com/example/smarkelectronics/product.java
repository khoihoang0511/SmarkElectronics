package com.example.smarkelectronics;

public class product {
    int idproduct;
    String imgavatar;
    String nameproduct;
    int priceproduct;
    String noteproduct;

    public product(int idproduct, String imgavatar, String nameproduct, int priceproduct, String noteproduct) {
        this.idproduct = idproduct;
        this.imgavatar = imgavatar;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.noteproduct = noteproduct;
    }

    public product() {
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getImgavatar() {
        return imgavatar;
    }

    public void setImgavatar(String imgavatar) {
        this.imgavatar = imgavatar;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public int getPriceproduct() {
        return priceproduct;
    }

    public void setPriceproduct(int priceproduct) {
        this.priceproduct = priceproduct;
    }

    public String getNoteproduct() {
        return noteproduct;
    }

    public void setNoteproduct(String noteproduct) {
        this.noteproduct = noteproduct;
    }
}
