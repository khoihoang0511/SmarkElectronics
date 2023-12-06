package com.example.smarkelectronics.Model;

public class NotificationModel {
    String imgNotification;
    String txtNameNotification;
    String txtContentNotification;
    String txtDateNotification;

    public NotificationModel(String imgNotification, String txtNameNotification, String txtContentNotification, String txtDateNotification) {
        this.imgNotification = imgNotification;
        this.txtNameNotification = txtNameNotification;
        this.txtContentNotification = txtContentNotification;
        this.txtDateNotification = txtDateNotification;
    }

    public String getImgNotification() {
        return imgNotification;
    }

    public void setImgNotification(String imgNotification) {
        this.imgNotification = imgNotification;
    }

    public String getTxtNameNotification() {
        return txtNameNotification;
    }

    public void setTxtNameNotification(String txtNameNotification) {
        this.txtNameNotification = txtNameNotification;
    }

    public String getTxtContentNotification() {
        return txtContentNotification;
    }

    public void setTxtContentNotification(String txtContentNotification) {
        this.txtContentNotification = txtContentNotification;
    }

    public String getTxtDateNotification() {
        return txtDateNotification;
    }

    public void setTxtDateNotification(String txtDateNotification) {
        this.txtDateNotification = txtDateNotification;
    }
}
