package com.retrofit.retrofitpractise;

public class LoginresponseModel {
    String status;

    public LoginresponseModel() {
    }

    public LoginresponseModel(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
