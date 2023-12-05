package com.example.smarkelectronics.Model;

public class OderModel {
    private int idmyoder;
    private int quantityorder;
    private String dayorder;
    private int status;
    private String nameProduct;
    private int costs;
    private int idaddress;
    private String imgProductOder;
    private int priceProductOder;

    public OderModel(int idmyoder, int quantityorder, String dayorder, int status, String nameProduct, int costs, int idaddress, String imgProductOder, int priceProductOder) {
        this.idmyoder = idmyoder;
        this.quantityorder = quantityorder;
        this.dayorder = dayorder;
        this.status = status;
        this.nameProduct = nameProduct;
        this.costs = costs;
        this.idaddress = idaddress;
        this.imgProductOder = imgProductOder;
        this.priceProductOder = priceProductOder;
    }

    public int getIdmyoder() {
        return idmyoder;
    }

    public void setIdmyoder(int idmyoder) {
        this.idmyoder = idmyoder;
    }

    public int getQuantityorder() {
        return quantityorder;
    }

    public void setQuantityorder(int quantityorder) {
        this.quantityorder = quantityorder;
    }

    public String getDayorder() {
        return dayorder;
    }

    public void setDayorder(String dayorder) {
        this.dayorder = dayorder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public int getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(int idaddress) {
        this.idaddress = idaddress;
    }

    public String getImgProductOder() {
        return imgProductOder;
    }

    public void setImgProductOder(String imgProductOder) {
        this.imgProductOder = imgProductOder;
    }

    public int getPriceProductOder() {
        return priceProductOder;
    }

    public void setPriceProductOder(int priceProductOder) {
        this.priceProductOder = priceProductOder;
    }
}
