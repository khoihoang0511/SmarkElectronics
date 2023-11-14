package com.example.smarkelectronics.Model;

public class Cart {
    int idcart;
    String imgavatar;
    String imgavatar2;
    String imgavatar3;
    String nameproduct;
    int priceproduct;
    int quanlitycart;

    public Cart(int idcart, String imgavatar, String imgavatar2, String imgavatar3, String nameproduct, int priceproduct, int quanlitycart) {
        this.idcart = idcart;
        this.imgavatar = imgavatar;
        this.imgavatar2 = imgavatar2;
        this.imgavatar3 = imgavatar3;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.quanlitycart = quanlitycart;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
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

    public int getQuanlitycart() {
        return quanlitycart;
    }

    public void setQuanlitycart(int quanlitycart) {
        this.quanlitycart = quanlitycart;
    }
}
