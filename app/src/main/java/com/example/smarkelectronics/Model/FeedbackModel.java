package com.example.smarkelectronics.Model;

public class FeedbackModel {
    String imgMyFeedback;
    String txtNameMyFeedback;
    int StarFeedback;
    String txtDayMonthYearFeedback;
    String txtContentFeedback;

    public FeedbackModel(String imgMyFeedback, String txtNameMyFeedback, int starFeedback, String txtDayMonthYearFeedback, String txtContentFeedback) {
        this.imgMyFeedback = imgMyFeedback;
        this.txtNameMyFeedback = txtNameMyFeedback;
        StarFeedback = starFeedback;
        this.txtDayMonthYearFeedback = txtDayMonthYearFeedback;
        this.txtContentFeedback = txtContentFeedback;
    }

    public String getImgMyFeedback() {
        return imgMyFeedback;
    }

    public void setImgMyFeedback(String imgMyFeedback) {
        this.imgMyFeedback = imgMyFeedback;
    }

    public String getTxtNameMyFeedback() {
        return txtNameMyFeedback;
    }

    public void setTxtNameMyFeedback(String txtNameMyFeedback) {
        this.txtNameMyFeedback = txtNameMyFeedback;
    }

    public int getStarFeedback() {
        return StarFeedback;
    }

    public void setStarFeedback(int starFeedback) {
        StarFeedback = starFeedback;
    }

    public String getTxtDayMonthYearFeedback() {
        return txtDayMonthYearFeedback;
    }

    public void setTxtDayMonthYearFeedback(String txtDayMonthYearFeedback) {
        this.txtDayMonthYearFeedback = txtDayMonthYearFeedback;
    }

    public String getTxtContentFeedback() {
        return txtContentFeedback;
    }

    public void setTxtContentFeedback(String txtContentFeedback) {
        this.txtContentFeedback = txtContentFeedback;
    }
}
