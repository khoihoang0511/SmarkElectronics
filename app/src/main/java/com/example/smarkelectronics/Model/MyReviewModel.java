package com.example.smarkelectronics.Model;

public class MyReviewModel {
    String imgMyreview;
    String txtNameMyReview;
    Integer txtProductPriceMyReview;
    Integer star;
    String txtDayMonthYear;
    String txtContent;

    public MyReviewModel(String imgMyreview, String txtNameMyReview, Integer txtProductPriceMyReview, Integer star, String txtDayMonthYear, String txtContent) {
        this.imgMyreview = imgMyreview;
        this.txtNameMyReview = txtNameMyReview;
        this.txtProductPriceMyReview = txtProductPriceMyReview;
        this.star = star;
        this.txtDayMonthYear = txtDayMonthYear;
        this.txtContent = txtContent;
    }

    public String getImgMyreview() {
        return imgMyreview;
    }

    public void setImgMyreview(String imgMyreview) {
        this.imgMyreview = imgMyreview;
    }

    public String getTxtNameMyReview() {
        return txtNameMyReview;
    }

    public void setTxtNameMyReview(String txtNameMyReview) {
        this.txtNameMyReview = txtNameMyReview;
    }

    public Integer getTxtProductPriceMyReview() {
        return txtProductPriceMyReview;
    }

    public void setTxtProductPriceMyReview(Integer txtProductPriceMyReview) {
        this.txtProductPriceMyReview = txtProductPriceMyReview;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getTxtDayMonthYear() {
        return txtDayMonthYear;
    }

    public void setTxtDayMonthYear(String txtDayMonthYear) {
        this.txtDayMonthYear = txtDayMonthYear;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }
}
