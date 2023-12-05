package com.example.smarkelectronics.Model;

public class ProfileModel {
    private String name;
    private String email;
    private int Numbertransport;
    private int Numberdelivery;
    private int NumberEvaluate;

    public ProfileModel(String name, String email, int numbertransport, int numberdelivery, int numberEvaluate) {
        this.name = name;
        this.email = email;
        Numbertransport = numbertransport;
        Numberdelivery = numberdelivery;
        NumberEvaluate = numberEvaluate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumbertransport() {
        return Numbertransport;
    }

    public void setNumbertransport(int numbertransport) {
        Numbertransport = numbertransport;
    }

    public int getNumberdelivery() {
        return Numberdelivery;
    }

    public void setNumberdelivery(int numberdelivery) {
        Numberdelivery = numberdelivery;
    }

    public int getNumberEvaluate() {
        return NumberEvaluate;
    }

    public void setNumberEvaluate(int numberEvaluate) {
        NumberEvaluate = numberEvaluate;
    }
}
