package com.example.smarkelectronics.Model;

public class Favorite {
    int idproduct;
    int idfavorite;
    String imgavatar;
    String imgavatar2;
    String imgavatar3;
    String nameproduct;
    int priceproduct;
    String noteproduct;

    public Favorite(int idproduct, int idfavorite, String imgavatar, String imgavatar2, String imgavatar3, String nameproduct, int priceproduct, String noteproduct) {
        this.idproduct = idproduct;
        this.idfavorite = idfavorite;
        this.imgavatar = imgavatar;
        this.imgavatar2 = imgavatar2;
        this.imgavatar3 = imgavatar3;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.noteproduct = noteproduct;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getIdfavorite() {
        return idfavorite;
    }

    public void setIdfavorite(int idfavorite) {
        this.idfavorite = idfavorite;
    }

    public String getImgavatar() {
        return imgavatar;
    }

    public void setImgavatar(String imgavatar) {
        this.imgavatar = imgavatar;
    }

    public String getImgavatar2() {
        return imgavatar2;
    }

    public void setImgavatar2(String imgavatar2) {
        this.imgavatar2 = imgavatar2;
    }

    public String getImgavatar3() {
        return imgavatar3;
    }

    public void setImgavatar3(String imgavatar3) {
        this.imgavatar3 = imgavatar3;
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
