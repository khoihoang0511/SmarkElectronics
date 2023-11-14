package com.example.smarkelectronics.Model;

public class Cart {
    int idcart;
    String nameproduct;
    int priceproduct;
    int quanlitycart;

    public Cart(int idcart, String nameproduct, int priceproduct, int quanlitycart) {
        this.idcart = idcart;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.quanlitycart = quanlitycart;
    }

    public Cart() {
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
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
