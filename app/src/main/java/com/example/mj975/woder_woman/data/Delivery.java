package com.example.mj975.woder_woman.data;

public class Delivery {
    private String name;
    private String address;
    private int postal;

    public Delivery(String name, String address, int postal) {
        this.name = name;
        this.address = address;
        this.postal = postal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }
}
