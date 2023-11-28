package com.example.smarkelectronics.Model;

public class CartCheckBoxModel extends Cart {
    private boolean check;

    public CartCheckBoxModel(int idcart, String imgavatar, String imgavatar2, String imgavatar3, String nameproduct, int priceproduct, int quanlitycart, boolean check) {
        super(idcart, imgavatar, imgavatar2, imgavatar3, nameproduct, priceproduct, quanlitycart);
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
