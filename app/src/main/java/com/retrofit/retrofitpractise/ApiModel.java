package com.retrofit.retrofitpractise;

public class ApiModel {
    String status;

    public ApiModel() {
    }

    public ApiModel(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
